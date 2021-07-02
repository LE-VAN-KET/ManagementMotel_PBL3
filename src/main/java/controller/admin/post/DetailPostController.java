package controller.admin.post;

import bean.CommentModel;
import bean.PostModel;
import constant.SystemConstant;
import service.ICommentService;
import service.IPostService;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/detail-post/*"})
public class DetailPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private ICommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId = Long.parseLong(request.getPathInfo().substring(1));
        try {
            PostModel postModel = postService.findByPostId(postId);
            List<CommentModel> commentModelList = commentService.findByPostId(postModel.getPostId());
            postModel.setCommentModelList(commentModelList);
            request.setAttribute(SystemConstant.POSTMODELS, postModel);
            request.setAttribute(SystemConstant.IMAGES, UploadFileUtil.getLinkImagesByFolderId(postModel.getLinkImages()));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/admin/DetailPost.jsp").forward(request, response);
    }
}
