package filter;

import bean.AccountModel;
import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebFilter("/message/*")
public class AuthorizationMessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                SystemConstant.ACCOUNTMODEL);
        if (accountModel == null) {
            response.sendRedirect("/home?message=not_permission_comment&&alert=danger");
            return;
        }

        if (accountModel.getUser().getFullName() == null || "".equals(accountModel.getUser().getFullName())
                || accountModel.getUser().getSDT() == null || "".equals(accountModel.getUser().getSDT())) {
            response.sendRedirect("/edit-profile?error=finish_filled_information_profile&&alert=danger");
        } else {
            filterChain.doFilter(new AuthenticatedRequest(request, accountModel.getUser().getUserId().toString()),
                    servletResponse);
        }
    }

//    private void returnUnauthorizedError(HttpServletResponse response, String message)
//            throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
//    }
//
//    private void returnAuthenticationError(HttpServletResponse response, String message)
//            throws IOException {
//        response.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, message);
//    }

    @Override
    public void destroy() {

    }

    private static class AuthenticatedRequest extends HttpServletRequestWrapper {

        private String userId;

        public AuthenticatedRequest(HttpServletRequest request, String userId) {
            super(request);
            this.userId = userId;
        }

        @Override
        public Principal getUserPrincipal() {
            return () -> userId;
        }
    }
}
