package filter;

import bean.AccountModel;
import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith("/register") || url.startsWith("/login")) {
            AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                    SystemConstant.ACCOUNTMODEL);
            if (accountModel != null) {
                /*permission access case role that*/
                switch (accountModel.getUser().getRoleModel().getRoleName()) {
                    case SystemConstant.ADMIN: {
                        response.sendRedirect("/admin");
                        break;
                    }
                    case SystemConstant.USER:
                    case SystemConstant.LANDLORD: {
                        response.sendRedirect("/home");
                        break;
                    }

                    default:
                        response.sendRedirect("/home");
                        break;
                }
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
