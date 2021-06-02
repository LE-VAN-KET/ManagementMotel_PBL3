package controller.admin.users;

import bean.UserModel;
import service.IRoleService;
import service.IUserService;
import utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-user"})
public class DeleteUserController extends HttpServlet {
    @Inject
    private IUserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel userModel = FormUtil.toModel(UserModel.class, request);
        userService.delete(userModel.getUserId());
        response.sendRedirect("/admin/user");
    }
}
