package filter;

import bean.AccountModel;
import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;

    public AuthorizationFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
//        check authorization
        if (url.startsWith("/admin")) {
            AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request, "ACCOUNTMODEL");
            if (accountModel != null) {
                /*permission access case role that*/
                switch (accountModel.getUser().getRoleMole().getRoleName()) {
                    case SystemConstant.LANDLORD: {
                        response.sendRedirect("/landlord");
                        break;
                    }
/*                    case SystemConstant.ADMIN: {
                        response.sendRedirect("/admin");
                        break;
                    }*/
                    case SystemConstant.USER: {
                        response.sendRedirect("/user");
                        break;
                    }
                }
            } else {
                /*
                * not permission access, done
                * redirect /home
                * */
                response.sendRedirect("/home");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
