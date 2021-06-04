package controller.admin.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
import service.IPostService;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-image/*"})
public class DeleteFilePostController extends HttpServlet {
    @Inject
    private IPostService postService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postSlug = request.getPathInfo().substring(1);
        String fileId = request.getParameter("fileId");
      /*  AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL);*/

        PostModel postModel = postService.findOneByPostSlug(postSlug);
        if ((postModel != null) /*&& (postModel.getUserModel().getUserId() ==
                accountModel.getUser().getUserId())*/) {
            UploadFileUtil.deleteFile(fileId);
        } else {
            response.sendRedirect("/admin/home?message=not_permission&&alert=danger");
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(fileId);
    }

}
