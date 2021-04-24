package controller.auth;

import service.IAccountService;
import utils.EmailUtil;

import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPassword extends HttpServlet {
    private String host;
    private String port;
    private String email;
    private String pass;

    @Inject
    private IAccountService accountService;

    ResourceBundle resourceBundleEmail = ResourceBundle.getBundle("mail");
    ResourceBundle resourceBundleMessaage = ResourceBundle.getBundle("message");

    @Override
    public void init() throws ServletException {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = resourceBundleEmail.getString("email");
        pass = resourceBundleEmail.getString("pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/ForgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String recipientEmail = req.getParameter("email");
            String passReset = accountService.resetAccountPassword(recipientEmail);
            if (passReset == null) {
                req.setAttribute("message", resourceBundleMessaage.getString("email_invalid"));
                req.getRequestDispatcher("/views/auth/ForgotPassword.jsp").forward(req,resp);
            } else {
                BodyPart content = new MimeBodyPart();
                content.setText(resourceBundleEmail.getString("msg") + " " + passReset + " "
                        + resourceBundleEmail.getString("link_reset_email"));

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(content);

                EmailUtil.sendMail(host, port, email, pass, recipientEmail,
                        resourceBundleEmail.getString("subject"), multipart);
                // success, done
                req.setAttribute("message", resourceBundleMessaage.getString("email_sent_success"));
                req.getRequestDispatcher("/views/auth/Login.jsp").forward(req, resp);
            }
        } catch (MessagingException e) {
            // failed, done
            e.printStackTrace();
            req.setAttribute("message", resourceBundleMessaage.getString("email_sent_failed"));
            req.getRequestDispatcher("/views/auth/ForgotPassword.jsp").forward(req,resp);
        }
    }
}
