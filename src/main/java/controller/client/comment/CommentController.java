package controller.client.comment;

import bean.AccountModel;
import bean.CommentModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.SystemConstant;
import service.ICommentService;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

//@WebServlet(urlPatterns = {"/comment/add"})
//public class CommentController extends HttpServlet {
//    @Inject
//    private ICommentService commentService;
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF8");
//        resp.setContentType("application/json;charset=utf-8");
//        Gson gson = new Gson();
//        JsonObject data = gson.fromJson(req.getReader(), JsonObject.class);
//        CommentModel commentModel = gson.fromJson(data, CommentModel.class);
//        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
//                SystemConstant.ACCOUNTMODEL);
//
//        PrintWriter out = resp.getWriter();
//        String urlRedirect = commentService.authorizationUser(accountModel);
//        if (urlRedirect != null) {
//            out.write("{\"urlRedirect\":\"" + urlRedirect + "\"}");
//            out.flush();
//            return;
//        }
//        commentModel.setUserId(accountModel.getUser().getUserId());
//        List<String> errors = commentService.validateComment(commentModel);
//        if (errors.isEmpty()) {
//            Long commentId = commentService.insert(commentModel);
//            commentModel.setCommentId(commentId);
//            Timestamp dattime = new Timestamp(System.currentTimeMillis());
//            String commentJson = gson.toJson(commentModel);
//            out.write("{\"errors\":\"\", \"createAt\":\"" + dateFormat.format(dattime) +
//                    "\", \"comment\":" + commentJson + "}");
//            out.flush();
//        } else {
//            out.write("{\"errors\":" + gson.toJson(errors) + "}");
//            out.flush();
//        }
//    }
//}
