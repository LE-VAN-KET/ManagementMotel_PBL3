package controller.client.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
import org.apache.log4j.Logger;
import paging.Pageble;
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

@WebServlet(urlPatterns = {"/personal-post"})
public class PersonalPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    private static final Logger logger = Logger.getLogger(PersonalPostController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pageble pageble = FormUtil.toModel(Pageble.class, req);
        String message = req.getParameter("message");
        pageble.setMaxPageItem(SystemConstant.MAXPAGEITEM);
        if (pageble.getPage() == null) {
            pageble.setPage(1);
        }

        List<PostModel> postModels = null;
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL);

        try {
            postModels = postService.findByUserId(pageble,accountModel.getUser().getUserId());
            pageble.setTotalItem(postService.getTotalIemByUserId(accountModel.getUser().getUserId()));
            pageble.setTotalPage(postService.getTotalItem(null));
//            for (PostModel post: postModels) {
//                post.setLinkImages(UploadFileUtil.getLinkOneImagesByFolderId(post.getLinkImages()));
//            }
            UploadFileUtil.getListLinkOneImagesByFolderId(postModels);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

        if (message != null) {
            req.setAttribute("message", resourceBundle.getString(message));
        }
        req.setAttribute(SystemConstant.PAGEABLE, pageble);
        req.setAttribute(SystemConstant.POSTMODELS, postModels);
        req.setAttribute(SystemConstant.ACCOUNTMODEL, accountModel);
        req.getRequestDispatcher("/views/client/layouts/PersonalPost.jsp").forward(req, resp);
    }
}
