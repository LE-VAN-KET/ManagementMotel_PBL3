package controller.admin.users;

import bean.PostModel;
import bean.RoleModel;
import bean.UserModel;
import com.google.api.services.drive.model.User;
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

@WebServlet(urlPatterns = {"/admin/edit-user"})
public class EditUserController extends HttpServlet {
    @Inject
    private IUserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel userModel = FormUtil.toModel(UserModel.class, request);
        Long roleId = Long.parseLong(request.getParameter("roleId"));
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(roleId);
        userModel.setRoleModel(roleModel);
        userService.update(userModel);
        response.sendRedirect("/admin/user");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        UserModel userModel = userService.findOne(userId);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(userModel);
    }
}
