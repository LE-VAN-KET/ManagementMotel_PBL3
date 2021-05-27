package controller.client.post;

import bean.AccountModel;
import bean.PostModel;
import constant.SystemConstant;
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

@WebServlet(urlPatterns = {"/personal-post"})
public class PersonalPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pageble pageble = FormUtil.toModel(Pageble.class, req);
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
            for (PostModel post: postModels) {
                post.setLinkImages(UploadFileUtil.getLinkOneImagesByFolderId(post.getLinkImages()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute(SystemConstant.PAGEABLE, pageble);
        req.setAttribute(SystemConstant.POSTMODELS, postModels);
        req.setAttribute(SystemConstant.ACCOUNTMODEL, accountModel);
        req.getRequestDispatcher("/views/client/layouts/PersonalPost.jsp").forward(req, resp);
    }
}
