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
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/admin/post"})
public class PostController extends HttpServlet {
    @Inject
    private IPostService postService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText");
        String message = request.getParameter("message");
        String alert = request.getParameter("alert");

        Pageble pageble = new Pageble();
        if (request.getParameter("page") == null) {
            pageble.setPage(1);
        } else {
            pageble.setPage(Integer.parseInt(request.getParameter("page")));
        }

        pageble.setTotalItem(postService.getTotalItem(searchText));
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);
        pageble.setTotalPage(pageble.getTotalItem());
        List<PostModel> postModels = postService.selectAll(searchText, pageble);
        if (alert != null && message != null) {
            request.setAttribute("message", resourceBundle.getString(message));
            request.setAttribute("alert", alert);
        }
        request.setAttribute("pageble", pageble);
        request.setAttribute("posts", postModels);
        request.getRequestDispatcher("../views/admin/Post.jsp").forward(request, response);
    }
}
