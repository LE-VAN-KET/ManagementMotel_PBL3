package controller.admin.users;

import bean.RoleModel;
import bean.UserModel;
import constant.SystemConstant;
import paging.Pageble;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/user"})
public class UserController extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private IRoleService roleService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        UserModel userModel = FormUtil.toModel(UserModel.class, request);
        Long roleId = Long.parseLong(request.getParameter("roleId"));
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(roleId);
        userModel.setRoleModel(roleModel);
        userService.insert(userModel);
        Map<String, String> errors = userService.validateVillage(userModel);
        if (errors.size() != 0) {
            request.setAttribute("errors_edit", errors);
            request.getRequestDispatcher("/admin/user").forward(request, response);
        } else {
            userService.insert(userModel);
            response.sendRedirect("/admin/user");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText");
        Pageble pageble = new Pageble();
        if (request.getParameter("page") == null) {
            pageble.setPage(1);
        } else {
            pageble.setPage(Integer.parseInt(request.getParameter("page")));
        }
        pageble.setTotalItem(userService.getTotalItem(searchText));
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);
        pageble.setTotalPage(pageble.getTotalItem());

        List<UserModel> userModels = userService.findAll(searchText, pageble);
        List<RoleModel> roleModels = roleService.findAll();

        request.setAttribute("pageble", pageble);
        request.setAttribute("users", userModels);
        request.setAttribute("roles", roleModels);
        request.getRequestDispatcher("../views/admin/User.jsp").forward(request, response);
    }
}
