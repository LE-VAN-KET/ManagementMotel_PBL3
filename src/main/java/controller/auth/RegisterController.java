package controller.auth;

import bean.AccountModel;
import bean.RoleModel;
import bean.UserModel;
import constant.SystemConstant;
import org.apache.log4j.Logger;
import service.IAccountService;
import service.IRoleService;
import utils.FormUtil;

import javax.inject.Inject;
import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    @Inject
    private IRoleService roleService;

    @Inject
    private IAccountService accountService;

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roles = roleService.findAll();
        req.setAttribute(SystemConstant.ROLEMODEL, roles);
        req.getRequestDispatcher("/views/auth/Register.jsp").forward(req, resp);
    }

    public RegisterController() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AccountModel accountModel = FormUtil.toModel(AccountModel.class, req);
        UserModel userModel = FormUtil.toModel(UserModel.class, req);
        RoleModel roleModel= FormUtil.toModel(RoleModel.class, req);
        // toLowerCase username
        accountModel.setUsername(accountModel.getUsername().toLowerCase());
        userModel.setRoleModel(roleModel);
        accountModel.setUser(userModel);
        String repeatPassword = req.getParameter("repeat-password");
        List<String> errors = accountService.registerValidation(accountModel, repeatPassword);
        if (errors.isEmpty()) {
            accountService.insert(accountModel);
            // create new account success
            logger.info("create new account successfully");
            resp.sendRedirect("/login");
            return;
        }
        req.setAttribute(SystemConstant.ERRORS, errors);
        logger.error("create account failed: several field invalid");
        doGet(req, resp);
    }
}
