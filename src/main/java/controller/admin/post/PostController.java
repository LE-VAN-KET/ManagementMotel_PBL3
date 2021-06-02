package controller.admin.post;

import bean.PostModel;
import constant.SystemConstant;
import paging.Pageble;
import service.IPostService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/post"})
public class PostController extends HttpServlet {
    @Inject
    private IPostService postService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageble pageble = new Pageble();
        if (request.getParameter("page") == null) {
            pageble.setPage(1);
        } else {
            pageble.setPage(Integer.parseInt(request.getParameter("page")));
        }
        pageble.setTotalItem(postService.getTotalItem());
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);
        List<PostModel> postModels = postService.selectAll(pageble);

        request.setAttribute("pageble", pageble);
        request.setAttribute("posts", postModels);
        request.getRequestDispatcher("../views/admin/Post.jsp").forward(request, response);
    }
}
