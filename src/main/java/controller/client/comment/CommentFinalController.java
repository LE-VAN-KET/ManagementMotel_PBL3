package controller.client.comment;

import bean.AccountModel;
import bean.CommentModel;
import bean.PostModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import configtor_WebSocket.Configurator;
import constant.SystemConstant;
import service.ICommentService;
import service.implement.CommentService;

import javax.ejb.Singleton;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@ServerEndpoint(value = "/comment/{postId}", configurator = Configurator.class)
public class CommentFinalController {
    private ICommentService commentService = CommentService.getInstance();

    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();
    private static final Gson gson = new Gson();
    private AccountModel accountModel;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @OnOpen
    public void connect(@PathParam("postId")String postId, Session session, EndpointConfig config) {
//        log.info("roomName={}", postId);
        List<CommentModel> commentModels = commentService.findAll();
        if (!rooms.containsKey(postId)) {
            // Create a room when the room does not exist
            Set<Session> room = new HashSet<>();
            room.add(session); //Add user
            rooms.put(postId, room);
        } else {
            // The room already exists, add users directly to the corresponding room
            rooms.get(postId).add(session);
        }

        if (config.getUserProperties().containsKey(SystemConstant.ACCOUNTMODEL)) {
            this.accountModel = (AccountModel) config.getUserProperties().get(SystemConstant.ACCOUNTMODEL);
        }
//        log.info("Client is connected");
    }

    @OnClose
    public void disConnect(@PathParam("postId") String postId, Session session) {
        rooms.get(postId).remove(session);
        System.out.println("Client has disconnected");
    }

    @OnMessage
    public void sendMessage(@PathParam("postId") String postId, String msg, Session session) {
        try {
            String urlRedirect = commentService.authorizationUser(this.accountModel);
            if (urlRedirect != null) {
                // send yourself redirect authorization
                sendMessageYourSelf(postId, "{\"urlRedirect\":\"" + urlRedirect + "\"}", session);
            } else {
                JsonObject data = gson.fromJson(msg, JsonObject.class);
                String state = gson.fromJson(data.get("method"), String.class);
                switch (state) {
                    case "add":
                        addMessage(postId, session, data);
                        break;
                    case "update":
                        // statement update
                        updateMessage(postId, session, data);
                        break;
                    case "delete":
                        deleteMessage(postId, session, data);
                        // statement delete
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
//            log.error("websocket message sending exception");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private void sendMessageYourSelf(String postId, String msg, Session session) throws IOException {
        for(Session sessionItem: rooms.get(postId)) {
            if (sessionItem.equals(session)) {
                // send yourself redirect authorization
                sessionItem.getBasicRemote().sendText(msg);
                break;
            }
        }
    }

    private void addMessage(String postId, Session session, JsonObject data) throws IOException {
        CommentModel commentModel = gson.fromJson(data, CommentModel.class);
        PostModel postModel = new PostModel();
        postModel.setPostId(Long.parseLong(postId));
        //save data to the database
        commentModel.setPostModel(postModel);
        commentModel.setUserModel(accountModel.getUser());
        // validate errors data input
        List<String> errors = commentService.validateComment(commentModel);
        if (errors.isEmpty()) {
            Long commentId = commentService.insert(commentModel);
            commentModel.setCommentId(commentId);
            Timestamp datetime = new Timestamp(System.currentTimeMillis());
//                    System.out.println(commentModel.getContent() + " " + commentModel.getUserId() + " " + commentModel.getPostId());
            for (Session sessionItem : rooms.get(postId)) {
                //send message to all everyone
                sessionItem.getBasicRemote().sendText("{\"createAt\":\"" + dateFormat.format(datetime) +
                        "\", \"comment\":" + gson.toJson(commentModel) + ", \"method\": \"add\"}");

//                  log.info("websocket message sent successfully");
            }
        } else {
            sendMessageYourSelf(postId, "{\"errors\":" + gson.toJson(errors) + ", " +
                    "\"method\": \"add\"}", session);
        }
    }

    private void deleteMessage(String postId, Session session, JsonObject data) throws IOException {
        CommentModel commentModel = gson.fromJson(data, CommentModel.class);
        if (commentModel != null && commentModel.getCommentId() != null) {
            // find comment by commentId
            commentModel = commentService.findByCommentId(commentModel.getCommentId());
             /*
             * validate comment exits, permit delete
             * auth postId == comment.post.postId
             * auth comment.userId == account.user.userId
             * */
            if ((commentModel != null) && (postId.equals(commentModel.getPostModel().getPostId().toString()))
            && (accountModel.getUser().getUserId().equals(commentModel.getUserModel().getUserId()))) {
                commentService.delete(commentModel.getCommentId());
                // send message delete element that comment.
                for (Session sessionItem : rooms.get(postId)) {
                    //send message to all everyone
                    sessionItem.getBasicRemote().sendText("{\"comment\":" +
                             gson.toJson(commentModel) + ", \"method\": \"delete\"}");
                    // delete comment successfully
                }
            } else {
                sendMessageYourSelf(postId, "{\"errors\":" +
                        gson.toJson(resourceBundle.getString("comment_not_exist")) + ", " +
                        "\"method\": \"delete\"}", session);
            }
        } else {
            sendMessageYourSelf(postId, "{\"errors\":" +
                    gson.toJson(resourceBundle.getString("comment_not_exist")) + ", " +
                    "\"method\": \"delete\"}", session);
        }
    }

    private void updateMessage(String postId, Session session, JsonObject data) throws IOException {
        CommentModel commentModel = gson.fromJson(data, CommentModel.class);
        if (commentModel != null && commentModel.getCommentId() != null) {
            // check content comment
            if(commentModel.getContent() == null || "".equals(commentModel.getContent())) {
                sendMessageYourSelf(postId, "{\"errors\":" +
                        gson.toJson(resourceBundle.getString("content_not_null")) + ", " +
                        "\"method\": \"update\"}", session);
                return;
            }
            // find comment by commentId
            CommentModel commentModelAuth = commentService.findByCommentId(commentModel.getCommentId());
            /*
             * validate comment exits, permit delete
             * auth postId == comment.post.postId
             * auth comment.userId == account.user.userId
             * */
            if ((commentModelAuth != null) && (postId.equals(commentModelAuth.getPostModel().getPostId().toString()))
                    && (accountModel.getUser().getUserId().equals(commentModelAuth.getUserModel().getUserId()))) {
                commentService.update(commentModel);
                commentModelAuth.setContent(commentModel.getContent());
                Timestamp datetime = new Timestamp(System.currentTimeMillis());
                // send message delete element that comment.
                for (Session sessionItem : rooms.get(postId)) {
                    //send message to all everyone
                    sessionItem.getBasicRemote().sendText("{\"modifiedAt\":\"" + dateFormat.format(datetime) +
                            "\", \"comment\":" + gson.toJson(commentModelAuth) + ", \"method\": \"update\"}");
                    // update comment successfully
                }
            } else {
                sendMessageYourSelf(postId, "{\"errors\":" +
                        gson.toJson(resourceBundle.getString("comment_not_exist")) + ", " +
                        "\"method\": \"update\"}", session);
            }
        } else {
            sendMessageYourSelf(postId, "{\"errors\":" +
                    gson.toJson(resourceBundle.getString("comment_not_exist")) + ", " +
                    "\"method\": \"update\"}", session);
        }
    }
}
