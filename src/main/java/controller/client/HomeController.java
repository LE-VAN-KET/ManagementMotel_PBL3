package controller.client;

import bean.DistrictModel;
import bean.PostModel;
import constant.SystemConstant;
import paging.Pageble;
import service.IDistrictService;
import service.IPostService;
import utils.FormUtil;
import utils.SessionUtil;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    @Inject
    private IDistrictService districtService;

    @Inject
    private IPostService postService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String villageId = req.getParameter("village");
        String square = req.getParameter("square");
        String price = req.getParameter("price");
        String message = req.getParameter("message");
        String alert = req.getParameter("alert");
        if (alert != null && message != null) {
            req.setAttribute("message", resourceBundle.getString(message));
            req.setAttribute("alert", alert);
        }
        List<DistrictModel> districtModels = districtService.selectViewAll();
        List<PostModel> postModels = null;
        Pageble pageble = FormUtil.toModel(Pageble.class, req);
        pageble.setTotalItem(postService.getTotalItem());
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);

        if (pageble.getPage() == null) {
            pageble.setPage(1);
        }

        try {
            if (villageId != null || square != null || price != null) {
                postModels = postService.findByParameters(villageId, square, price, pageble);
            } else {
                postModels = postService.selectAll(pageble);
            }

            for (PostModel post: postModels) {
                post.setLinkImages(UploadFileUtil.getLinkOneImagesByFolderId(post.getLinkImages()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute(SystemConstant.ACCOUNTMODEL,
                SessionUtil.getInstance().getValue(req, SystemConstant.ACCOUNTMODEL));
        req.setAttribute(SystemConstant.PAGEABLE, pageble);
        req.setAttribute(SystemConstant.POSTMODELS, postModels);
        req.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
        req.getRequestDispatcher("/views/client/layouts/Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
