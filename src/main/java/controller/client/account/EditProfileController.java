package controller.client.account;

import bean.AccountModel;
import bean.UserModel;
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
import java.util.List;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/edit-profile"})
public class EditProfileController extends HttpServlet {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Inject
    private IAccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String alert = req.getParameter("alert");
        if (alert != null && message != null) {
            req.setAttribute("message", resourceBundle.getString(message));
            req.setAttribute("alert", alert);
        }
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL);
        req.setAttribute(SystemConstant.ACCOUNTMODEL, accountModel);
        req.getRequestDispatcher("/views/client/layouts/EditProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserModel userModel = FormUtil.toModel(UserModel.class, req);
        AccountModel accountModel = FormUtil.toModel(AccountModel.class, req);
        AccountModel accountModelOld = (AccountModel) SessionUtil.getInstance().getValue(req, SystemConstant.ACCOUNTMODEL);
        userModel.setUserId(accountModelOld.getUser().getUserId());
        accountModel.setUser(userModel);

        /*
        * the case of not changing username or email
        * set username or email old*/
        if (accountModel.getUser().getEmail() == null) {
            accountModel.getUser().setEmail(accountModelOld.getUser().getEmail());
        }

        if (accountModel.getUsername() == null) {
            accountModel.setUsername(accountModelOld.getUsername());
        }

        try {
            List<String> errors = accountService.validateEdit(accountModel);
            if (!errors.isEmpty()) {
                req.setAttribute(SystemConstant.ERRORS, errors);
                doGet(req, resp);
            } else {
                accountModel.setAccountId(accountModelOld.getAccountId());
                accountModel.getUser().setRoleModel(accountModelOld.getUser().getRoleModel());

                // update information user
                accountService.editProfile(accountModel);
                SessionUtil.getInstance().putValue(req, SystemConstant.ACCOUNTMODEL, accountModel);
                resp.sendRedirect("/home");
            }
        } catch (Exception e) {
            resp.sendRedirect("/home?message=edit_failed&&alert=danger");
            e.printStackTrace();
        }
    }
}
