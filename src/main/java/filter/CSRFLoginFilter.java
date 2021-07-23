package filter;

import constant.SystemConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@WebFilter(urlPatterns = {"/login"})
public class CSRFLoginFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CSRFLoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        // if session csrf not generation then add session csrf token
        if (session.getAttribute(SystemConstant.CSRFTOKEN) == null) {
            session.setAttribute(SystemConstant.CSRFTOKEN, generateToken());
            session.setMaxInactiveInterval(10*60);
        }
        String accessDeniedReason = null;
        // if method POST then check csrf token
        if ("POST".equals(httpServletRequest.getMethod())) {
            /*
             * Verifying Same Origin with Standard Headers
             * */
            // get source from the Origin header
            String origin = httpServletRequest.getHeader("Origin");
            String referer = httpServletRequest.getHeader("Referer");
            if (this.isBlank(origin)) {
                if (this.isBlank(referer)) {
                    // If origin and referer are both empty is empty then we trace the event and we block the request
                    accessDeniedReason = "ORIGIN and REFERER request headers are both absent/empty so we block the request!";
                    logger.warn(accessDeniedReason);
                    httpServletResponse.sendError(httpServletResponse.SC_FORBIDDEN, accessDeniedReason);
                    return;
                }
            } else if (!this.isBlank(referer)) {
                // if origin not empty and referer empty/null
                //Compare the source origin and referer
                URL sourceOrigin = new URL(origin);
                URL sourceReferer = new URL(referer);
                if (!sourceOrigin.getProtocol().equals(sourceReferer.getProtocol())
                        || !sourceOrigin.getHost().equals(sourceReferer.getHost())
                        || sourceOrigin.getPort() != sourceReferer.getPort()) {
                    //not match so we trace the event and we block the request
                    accessDeniedReason = String.format("CSRFLoginFilter: Protocol/Host/Port do not fully matches so we " +
                            "block the request! (%s != %s) ", sourceOrigin, sourceReferer);
                    logger.warn(accessDeniedReason);
                    httpServletResponse.sendError(httpServletResponse.SC_FORBIDDEN, accessDeniedReason);
                    return;
                }
            }

            /*
             * Verifying CSRF token
             * */
            String clientToken = httpServletRequest.getParameter("_CSRFToken");
            String serverToken = session.getAttribute(SystemConstant.CSRFTOKEN).toString();
            if(!this.isBlank(clientToken) && clientToken.equals(serverToken)){
                chain.doFilter(request, response);
            } else {
                accessDeniedReason = "CSRFLoginFilter: Token provided client-side and server-side are not " +
                        "equals so we block the request!";
                logger.warn(accessDeniedReason);
                httpServletResponse.sendError(httpServletResponse.SC_FORBIDDEN, accessDeniedReason);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        logger.info("CSRFLoginFilter: Filter shutdown");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Generate a new CSRF token
     * @return The token a string
     */
    private String generateToken() {
        SecureRandom secureRandom = null;
        byte[] buf = new byte[50];
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] seed = secureRandom.generateSeed(50);
            secureRandom.setSeed(seed);
            secureRandom.nextBytes(buf);
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
            logger.warn("generateToke failed", e);
        }
        return (secureRandom != null) ? String.valueOf(secureRandom.nextLong()) : null;
    }
}
