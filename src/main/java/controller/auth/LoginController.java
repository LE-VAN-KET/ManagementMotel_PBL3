package controller.auth;

import bean.AccountModel;
import constant.SystemConstant;
import service.IAccountService;
import utils.FormUtil;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Inject
    private IAccountService accountService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String alert = req.getParameter("alert");
        if (alert != null && message != null) {
            req.setAttribute("message", resourceBundle.getString(message));
            req.setAttribute("alert", alert);
        }
        req.getRequestDispatcher("/views/auth/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountModel accountModel = FormUtil.toModel(AccountModel.class, req);
        // toLowerCase Username
        accountModel.setUsername(accountModel.getUsername().toLowerCase());
        accountModel = accountService.findByUserNameAndPassword(accountModel.getUsername(),
                accountModel.getPassword());
        if (accountModel != null) {
            SessionUtil.getInstance().putValue(req, "ACCOUNTMODEL", accountModel);
            switch (accountModel.getUser().getRoleMole().getRoleName()) {
                case SystemConstant.ADMIN:
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                    break;
                case SystemConstant.LANDLORD:
                    resp.sendRedirect(req.getContextPath() + "/home");
                    break;
                case SystemConstant.USER:
                    resp.sendRedirect(req.getContextPath() + "/home-user");
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath()
                    + "/login?message=username_password_invalid&&alert=danger");
        }
    }
}