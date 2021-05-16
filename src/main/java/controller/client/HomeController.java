package controller.client;

import bean.DistrictModel;
import bean.PostModel;
import constant.SystemConstant;
import service.IDistrictService;
import service.IPostService;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    @Inject
    private IDistrictService districtService;

    @Inject
    private IPostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DistrictModel> districtModels = districtService.selectViewAll();
        List<PostModel> postModels = postService.selectAll();
        for (PostModel post: postModels) {
            post.setLinkImages(UploadFileUtil.getLinkOneImagesByFolderId(post.getLinkImages()));
        }
        req.setAttribute(SystemConstant.POSTMODELS, postModels);
        req.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
        req.getRequestDispatcher("/views/client/layouts/Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String districtId = req.getParameter("villageId");
        String square = req.getParameter("data-square");
        String price = req.getParameter("data-price");
        try {
            List<DistrictModel> districtModels = districtService.selectViewAll();
            List<PostModel> postModels = postService.findByParameters(districtId, square,price);
            for (PostModel post: postModels) {
                post.setLinkImages(UploadFileUtil.getLinkOneImagesByFolderId(post.getLinkImages()));
            }
            req.setAttribute(SystemConstant.POSTMODELS, postModels);
            req.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
            req.getRequestDispatcher("/views/client/layouts/Home.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
