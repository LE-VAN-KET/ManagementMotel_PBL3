package controller.client.post;

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

@WebServlet(urlPatterns = {"/post/show/*"})
public class DetailPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postSlug = req.getPathInfo().substring(1);
        try {
            PostModel postModel = postService.findOneByPostSlug(postSlug);
            req.setAttribute(SystemConstant.POSTMODELS, postModel);
            req.setAttribute(SystemConstant.IMAGES, UploadFileUtil.getLinkImagesByFolderId(postModel.getLinkImages()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/views/client/layouts/DetailPost.jsp").forward(req, resp);
    }
}
