package controller.auth;

import constant.SystemConstant;
import org.apache.log4j.Logger;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtil.getInstance().removeValue(req, SystemConstant.ACCOUNTMODEL);
        logger.info("user logout success");
        resp.sendRedirect("/login?message=logout_success&alert=success");
    }
}
