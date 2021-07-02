package controller.admin.comment;

import service.ICommentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/remove-comment"})
public class CommentController extends HttpServlet {
    @Inject
    private ICommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long commentId = Long.parseLong(request.getParameter("commentId"));
        commentService.delete(commentId);
    }
}
