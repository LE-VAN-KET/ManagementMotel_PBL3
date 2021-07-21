package controller.client.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
import org.apache.log4j.Logger;
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

@WebServlet(urlPatterns = {"/post/delete-image/*"})
public class DeleteFilePostController extends HttpServlet {
    @Inject
    private IPostService postService;

    private static final Logger logger = Logger.getLogger(DeleteFilePostController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postSlug = req.getPathInfo().substring(1);
        String fileId = req.getParameter("fileId");
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL);

        PostModel postModel = postService.findOneByPostSlug(postSlug);
        if ((postModel != null) && (postModel.getUserModel().getUserId() ==
                accountModel.getUser().getUserId())) {
            UploadFileUtil.deleteFile(fileId);
            logger.info("delete image post success");
        } else {
            resp.sendRedirect("home?message=not_permission&&alert=danger");
            logger.error("delete image post failed: not permit delete");
        }
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(fileId);
    }
}
