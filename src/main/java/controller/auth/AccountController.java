package controller.admin;

import bean.AccountModel;
import service.IAccountService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/account"})
public class AccountController extends HttpServlet {
    @Inject
    private IAccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AccountModel> accountModels = accountService.selectAll();
        request.setAttribute("accounts",accountModels);
        request.getRequestDispatcher("../views/admin/Account.jsp").forward(request, response);
    }
}
