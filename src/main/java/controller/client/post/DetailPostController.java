package controller.client.post;

import bean.CommentModel;
import bean.PostModel;
import constant.SystemConstant;
import org.apache.log4j.Logger;
import service.ICommentService;
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
import java.util.List;

@WebServlet(urlPatterns = {"/post/show/*"})
public class DetailPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private ICommentService commentService;

    private static final Logger logger = Logger.getLogger(DetailPostController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postSlug = req.getPathInfo().substring(1);
        try {
            PostModel postModel = postService.findOneByPostSlug(postSlug);
            List<CommentModel> commentModelList = commentService.findByPostId(postModel.getPostId());
            postModel.setCommentModelList(commentModelList);
            req.setAttribute(SystemConstant.POSTMODELS, postModel);
            req.setAttribute(SystemConstant.IMAGES, UploadFileUtil.getLinkImagesByFolderId(postModel.getLinkImages()));
            req.setAttribute(SystemConstant.ACCOUNTMODEL,  SessionUtil.getInstance().getValue(req,
                    SystemConstant.ACCOUNTMODEL));
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

        req.getRequestDispatcher("/views/client/layouts/DetailPost.jsp").forward(req, resp);
    }
}
