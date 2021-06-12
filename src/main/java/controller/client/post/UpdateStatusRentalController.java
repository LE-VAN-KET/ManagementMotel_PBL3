package controller.client.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
import service.IPostService;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/post/update-status-rental/*"})
public class UpdateStatusRentalController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF8");
        resp.setContentType("application/json");
        try {
            Long postId = Long.parseLong(req.getPathInfo().substring(1));
            AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                    SystemConstant.ACCOUNTMODEL);
            Boolean statusRental = Boolean.parseBoolean(req.getParameter("statusRental"));


            PostModel postModel = postService.findByPostId(postId);
            if ((postModel != null) && (postModel.getUserModel().getUserId() ==
                    accountModel.getUser().getUserId())) {
                postService.updateStatusRentalByPostId(postId, !statusRental);
                out.write("{\"success\":\"OK\", \"statusRental\":" + !statusRental + "}");
                out.flush();
            } else {
                out.write("{\"success\":\"fail\"}");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"success\":\"fail\"}");
            out.flush();
        }
    }
}
