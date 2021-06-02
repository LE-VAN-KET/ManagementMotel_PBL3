package controller.admin.post;

import service.IPostService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-post"})
public class DeletePostController extends HttpServlet {
    @Inject
    private IPostService postService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId = Long.parseLong(request.getParameter("postId"));
        postService.deleteByPostId(postId);
        response.sendRedirect("/admin/post");
    }
}
