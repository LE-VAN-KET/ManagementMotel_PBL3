package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/test"})
public class TestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        boolean isError = false;
        if(username == null || "".equals(username)) {
            request.setAttribute("username_error","Field username is not empty");
            isError = true;
        } else if(username.length() < 0 || username.length() > 5) {
            request.setAttribute("username_error","Field username have length from 1 to 5");
            isError = true;
        }

        if(password == null || "".equals(password)) {
            request.setAttribute("password_error","Field password is not empty");
            isError = true;
        }

        if(phone == null || "".equals(phone)) {
            request.setAttribute("phone_error", "Field phone is not empty");
            isError = true;
        } else if(!phone.matches("[0-9]+")) {
            request.setAttribute("phone_error","Field phone must digits");
        }

        if(email == null || "".equals(email)) {
            request.setAttribute("email_error","Field email is not empty");
            isError = true;
        } else if(!email.matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")){
            request.setAttribute("email_error","Field email is not syntax");
        }
        Map<String, String> errors = new HashMap<>();
        errors.put("name","Wrong");
        errors.put("nik","Correct");
        request.setAttribute("errors",errors);
        if(!isError) {
            request.setAttribute("success","Login successfully");
        }
        request.getRequestDispatcher("../views/admin/Test.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
