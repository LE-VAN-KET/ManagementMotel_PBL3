package controller.client.message;

import bean.MessageModel;
import bean.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.MessageType;
import controller.client.message.decoder.MessageDecoder;
import notificationMessage.NotificationMessage;
import service.IMessageService;
import service.implement.MessageService;
import socket_common.SocketRooms;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/message/{senderId}",
        decoders = MessageDecoder.class)
public class MessageSocket implements Serializable {
    // map store list key = userId and val = session user
    private static Map<String, Set<Session>> users = new ConcurrentHashMap<>();
    // set store list user online
    private Set<String> usersOnline = Collections.synchronizedSet(new HashSet<>());
    // format datetime send client
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private String nameRoom;
    private Gson gson = new Gson();

    private IMessageService messageService = MessageService.getInstance();

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @OnOpen
    public void connect(@PathParam("senderId")String senderId, Session session) throws IOException {
        // concatenating senderId_recipientId
//        this.nameRoom = senderId + "_" + recipientId;
        String user_id = session.getUserPrincipal().getName();
        if (user_id.equals(senderId)) {
            SocketRooms.connection(senderId, users, session);
        } else {
            disConnect(senderId, session);
        }
//        if ((user_id != null) && (!users.contains(user_id))) {
//            users.add(user_id);
//        }
//        log.info("Client is connected");
    }

    @OnClose
    public void disConnect(@PathParam("senderId")String senderId, Session session) throws IOException {
        users.get(senderId).remove(session);
        // check if currently not user online -> remove that room
        if (users.get(senderId).isEmpty()) {
            users.remove(senderId);
            // refresh list user online
            refreshUsersOnline();
            usersOnline.clear();
        }
    }

    @OnMessage
    public void sendMessage(@PathParam("senderId")String senderId, JsonObject data, Session session) {
        try {
            String state = gson.fromJson(data.get("messageType"), String.class);
            switch (state) {
                case MessageType.ADD:
                    addMessage(senderId, session, data);
                    break;
                case MessageType.DELETE:
                    deleteMessage(senderId, session, data);
                    // statement delete
                    break;
                case MessageType.MSG_HISTORY:
                    historyMessage(session, data);
                    break;
                case MessageType.LIST_LAST_MESSAGE:
                    listLastMessage(senderId);
                    break;
                case MessageType.LIST_USERS_ONLINE:
                    listUsersOnline(senderId);
                    break;
                case MessageType.REFRESH_USERS_ONLINE:
                    refreshUsersOnline();
                    break;
                case MessageType.UPDATE_MSG_SEEN:
                    updateMessageSeen(senderId, data);
                    break;
                case MessageType.NOTIFICATION_MSG:
                    notificationMessage(senderId);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessageYourSelf(String msg, Session session) throws IOException {
        session.getBasicRemote().sendText(msg);
    }

    private void broadcast(String nameRoom, String messageToSend) throws IOException {
        //broadcasting to all connected clients in the room
        for (Session session: users.get(nameRoom)) {
            session.getBasicRemote().sendText(messageToSend);
        }
    }

    private void addMessage(String sender_id, Session session, JsonObject data) throws IOException {
        MessageModel messageModel = gson.fromJson(data, MessageModel.class);
        String senderId = gson.fromJson(data.get("senderId"), String.class);
        String recipientId = gson.fromJson(data.get("recipientId"), String.class);

        UserModel sender = new UserModel();
        sender.setUserId(Long.parseLong(senderId));
        messageModel.setSender(sender);

        UserModel recipient = new UserModel();
        recipient.setUserId(Long.parseLong(recipientId));
        messageModel.setRecipient(recipient);

        messageModel.setSeen(false);
        // validate errors data input
        List<String> errors = messageService.validateMessage(messageModel);
        if (!sender_id.equals(session.getUserPrincipal().getName())) {
            errors.add(resourceBundle.getString("not_send_message"));
        }
        if (errors.isEmpty()) {
            Long messageId = messageService.insert(messageModel);
            messageModel.setMessageId(messageId);
            messageModel.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
            String msg = "{\"message\":" + gson.toJson(messageModel) + ", \"messageType\": " +
                    gson.toJson(MessageType.ADD) + "}";
            // send message to all everyone in room senderId_recipientId
            broadcast(senderId, msg);
            listLastMessage(senderId);
            // send message to all everyone in room recipientId_senderId
//            String recipientId_senderId = recipientId.concat("_" + senderId);
            if (users.containsKey(recipientId)) {
                broadcast(recipientId, msg);
                listLastMessage(recipientId);
                // notification message
                notificationMessage(recipientId);
            }
            // log.info("websocket message sent successfully");
        } else {
            sendMessageYourSelf( "{\"errors\":" + gson.toJson(errors) + ", " +
                    "\"messageType\": " + gson.toJson(MessageType.ADD) + "}", session);
        }
    }

    private void deleteMessage(String sender_id, Session session, JsonObject data) throws IOException {
        MessageModel messageModel = gson.fromJson(data, MessageModel.class);
        if (messageModel != null && messageModel.getMessageId() != null) {
            // find message by messageId
            messageModel = messageService.findByMessageId(messageModel.getMessageId());
            /*
             * validate message exits, permit delete
             * auth senderId == account.user.userId
             * */
            if ((messageModel != null) &&
                    (session.getUserPrincipal().getName().equals(messageModel.getSender().getUserId().toString()))) {
                messageService.delete(messageModel.getMessageId());
                // send message delete element this message successfully.
                String msg = "{\"message\":" + gson.toJson(messageModel) + ", \"messageType\": " +
                        gson.toJson(MessageType.DELETE) + "}";
                //send message to all everyone in room senderId_recipientId
                broadcast(sender_id, msg);
                listLastMessage(sender_id);
                //send message to all everyone in room recipientId_senderId
//                String recipientId_senderId = messageModel.getRecipient().getUserId().toString() + "_" +
//                        messageModel.getSender().getUserId().toString();
                String recipientId = messageModel.getRecipient().getUserId().toString();
                if (users.containsKey(recipientId)) {
                    broadcast(recipientId, msg);
                    listLastMessage(recipientId);
                }
            } else {
                sendMessageYourSelf( "{\"errors\":" +
                        gson.toJson(resourceBundle.getString("message_not_exist")) + ", " +
                        "\"messageType\": " + gson.toJson(MessageType.DELETE) + "}", session);
            }
        } else {
            sendMessageYourSelf("{\"errors\":" +
                    gson.toJson(resourceBundle.getString("message_not_exist")) + ", " +
                    "\"messageType\": " + gson.toJson(MessageType.DELETE) + "}", session);
        }
    }

    private void historyMessage(Session session, JsonObject data) throws IOException {
        Long senderId = gson.fromJson(data.get("senderId"), Long.class);
        Long recipientId = gson.fromJson(data.get("recipientId"), Long.class);
        List<MessageModel> messageModelList = messageService.findBySenderIdOrRecipientId(senderId, recipientId);
        session.getBasicRemote().sendText("{\"messages\":" + gson.toJson(messageModelList) + ", \"messageType\":" +
                gson.toJson(MessageType.MSG_HISTORY) + "}");
    }

    private void listLastMessage(String userId) throws IOException {
        List<MessageModel> messageModelList = messageService.getLastMessageChat(Long.parseLong(userId));
        String msg = "{\"listLastMessage\": " + gson.toJson(messageModelList) + ", \"messageType\":"
                + gson.toJson(MessageType.LIST_LAST_MESSAGE) + "}";
        broadcast(userId, msg);
    }

    private void listUsersOnline(String userId) throws IOException {
        List<MessageModel> messageModelList = messageService.getLastMessageChat(Long.parseLong(userId));
        usersOnline.clear();
        // list friend send message
        for(MessageModel messageModel: messageModelList) {
            String receiverId = (messageModel.getSender().getUserId().toString().equals(userId)) ?
                    messageModel.getRecipient().getUserId().toString()
                    : messageModel.getSender().getUserId().toString();
            usersOnline.add(receiverId);
        }

        reloadListUsersOnline();

        String msg = "{\"listUsersOnline\": " + gson.toJson(usersOnline) + ", \"messageType\":"
                + gson.toJson(MessageType.LIST_USERS_ONLINE) + "}";
        broadcast(userId, msg);
    }

    private void reloadListUsersOnline() {
        Iterator<String> listUser = usersOnline.iterator();
        while (listUser.hasNext()) {
            if (!users.containsKey(listUser.next())) {
                listUser.remove();
            }
        }
    }

    private void refreshUsersOnline() throws IOException {
        for (String user_id: usersOnline) {
            String message = "{\"messageType\":" + gson.toJson(MessageType.REFRESH_USERS_ONLINE) + "}";
            broadcast(user_id, message);
        }
    }

    private void updateMessageSeen(String senderId, JsonObject data) throws IOException {
        Long recipientId = gson.fromJson(data.get("recipientId"), Long.class);
        messageService.updateSeen(recipientId, Long.parseLong(senderId), true);
        if (users.containsKey(recipientId.toString())) {
            List<MessageModel> messageModelList = messageService.findBySenderIdOrRecipientId(
                    Long.parseLong(senderId), recipientId);
            String msg = "{\"messages\":" + gson.toJson(messageModelList) + ", \"messageType\":" +
                    gson.toJson(MessageType.MSG_HISTORY) + "}";
            broadcast(recipientId.toString(), msg);
        }
    }

    private void notificationMessage(String senderId) throws IOException {
        List<NotificationMessage> notificationMessages = messageService.notificationMessage(Long.parseLong(senderId),
                false);
        String msg = "{\"notificationMessages\": " + gson.toJson(notificationMessages) + ", \"messageType\": "
                + gson.toJson(MessageType.NOTIFICATION_MSG) + "}";
        broadcast(senderId, msg);
    }
}
