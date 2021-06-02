package controller.admin.post;

import bean.PostModel;
import service.IPostService;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/update-status-post/*"})
public class UpdateStatusPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId = Long.parseLong(request.getPathInfo().substring(1));
        boolean statusPost = Boolean.parseBoolean(request.getParameter("statusPost"));
      /*  AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL);*/

        postService.updateStatusPostByPostId(postId, statusPost);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }
}
