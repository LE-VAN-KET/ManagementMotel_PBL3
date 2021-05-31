package filter;

import bean.AccountModel;
import constant.SystemConstant;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationPostFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
//        check authorization post
        if (url.startsWith("/post/show")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (url.startsWith("/post") || url.startsWith("/personal-post")) {
            AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                    SystemConstant.ACCOUNTMODEL);
            if (accountModel != null) {
                // check information account
                if (accountModel.getUser().getFullName() == null || "".equals(accountModel.getUser().getFullName())
                        || accountModel.getUser().getEmail() == null || "".equals(accountModel.getUser().getEmail())
                        || accountModel.getUser().getSDT() == null || "".equals(accountModel.getUser().getSDT())) {
                    response.sendRedirect("/edit-profile?error=finish_filled_information_profile&&alert=danger");
                    return;
                }
                /*permission access case role that*/
                switch (accountModel.getUser().getRoleMole().getRoleName()) {
                    case SystemConstant.ADMIN: {
                        response.sendRedirect("/admin");
                        break;
                    }
                    case SystemConstant.LANDLORD: {
                        filterChain.doFilter(servletRequest, servletResponse);
                        break;
                    }
                    case SystemConstant.USER: {
                        response.sendRedirect("/home?message=not_permission&&alert=danger");
                        break;
                    }
                }
            } else {
                /*
                 * not permission access, done
                 * redirect /home
                 * */
                response.sendRedirect("/login?message=not_permission&&alert=danger");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
