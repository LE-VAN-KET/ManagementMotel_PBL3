package filter;

import bean.AccountModel;
import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationUser implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                SystemConstant.ACCOUNTMODEL);
        if (accountModel.getUser().getFullName() == null || "".equals(accountModel.getUser().getFullName())
            || accountModel.getUser().getEmail() == null || "".equals(accountModel.getUser().getEmail())
            || accountModel.getUser().getSDT() == null || "".equals(accountModel.getUser().getSDT())) {
            response.sendRedirect("/edit-profile?error=finish_filled_information_profile&&alert=danger");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
