package controller.client.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
import service.IPostService;
import utils.SessionUtil;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/personal-post/delete/*"})
public class DeletePostController extends HttpServlet {
    @Inject
    private IPostService postService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long postId = Long.parseLong(req.getPathInfo().substring(1));
            PostModel postModel = postService.findByPostId(postId);
            if (postId != null) {
                AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                        SystemConstant.ACCOUNTMODEL);
                if (postModel.getUserModel().getUserId() != accountModel.getUser().getUserId()) {
                    throw new Exception("not permission");
                }
                postService.deleteByPostId(postId);
                UploadFileUtil.deleteFile(postModel.getLinkImages());
                req.setAttribute("message", resourceBundle.getString("delete_success"));
            } else {
                throw new Exception("not permission");
            }
        } catch (Exception e) {
            req.setAttribute("message", resourceBundle.getString("delete_failed"));
            e.printStackTrace();
        } finally {
            resp.sendRedirect("/personal-post");
        }
    }
}
