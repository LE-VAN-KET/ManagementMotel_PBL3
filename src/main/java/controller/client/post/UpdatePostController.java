package controller.client.post;

import bean.*;
import constant.SystemConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.IDistrictService;
import service.IPostService;
import utils.SessionUtil;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/post/update/*"})
public class UpdatePostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private IDistrictService districtService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postSlug = req.getPathInfo().substring(1);
        try {
            PostModel postModel = postService.findOneByPostSlug(postSlug);
            if (postModel != null) {
                AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                        SystemConstant.ACCOUNTMODEL);
                if (postModel.getUserModel().getUserId() != accountModel.getUser().getUserId()) {
                    resp.sendRedirect("/home?message=not_permission&&alert=danger");
                }
                req.setAttribute(SystemConstant.POSTMODEL, postModel);
                req.setAttribute(SystemConstant.IMAGES, UploadFileUtil.getLinkImagesByFolderId(postModel.getLinkImages()));
                req.setAttribute(SystemConstant.ACCOUNTMODEL, accountModel);
                List<DistrictModel> districtModels = districtService.selectViewAll();
                req.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
                req.getRequestDispatcher("/views/client/layouts/EditArticle.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("home?message=not_permission&&alert=danger");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/home?message=not_permission&&alert=danger");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // checks if the request actually contains upload file
        if (ServletFileUpload.isMultipartContent(req))  {
            try {
                Long postId = Long.parseLong(req.getPathInfo().substring(1));
                PostModel postModelOld = postService.findByPostId(postId);
                AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                        SystemConstant.ACCOUNTMODEL);
                if ((postModelOld == null) || (postModelOld.getUserModel().getUserId() !=
                        accountModel.getUser().getUserId())) {
                    // error post not exist or user not permission
                    resp.sendRedirect("/home?message=not_permission&&alert=danger");
                    return;
                }
                List<FileItem> formItems = null;
                Iterator iter = null;
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                upload.setHeaderEncoding("UTF-8");
                formItems = upload.parseRequest(req);
                iter = formItems.iterator();
                List<FileItem> listImages = new ArrayList<FileItem>();
                PostModel postModel = new PostModel();
                VillageModel villageModel = new VillageModel();
                UserModel userModel = new UserModel();

                if (formItems != null && formItems.size() > 0) {
                    while (iter.hasNext())  {
                        FileItem item = (FileItem) iter.next();
                        // processes only fields that are not form fields
                        if (item.isFormField()) {
                            if (item.getFieldName().equals("villageId")) {
                                BeanUtils.setProperty(villageModel, item.getFieldName(), item.getString("UTF-8"));
                            } else {
                                BeanUtils.setProperty(postModel, item.getFieldName(), item.getString("UTF-8"));
                            }
                        } else {
                            listImages.add(item);
                        }
                    }

                    postModel.setPostId(postModelOld.getPostId());
                    userModel.setUserId(accountModel.getUser().getUserId());
                    postModel.setUserModel(userModel);
                    postModel.setVillageModel(villageModel);
                    postModel.setLinkImages(postModelOld.getLinkImages());
                    postModel.setStatusPost(postModelOld.getStatusPost());
                    postModel.setStatusRental(postModelOld.getStatusRental());
                    List<String> errors = postService.validatePost(postModel);
                    if (!errors.isEmpty()) {
                        req.setAttribute(SystemConstant.ERRORS, errors);
                        doGet(req, resp);
                        return;
                    }
                    postService.updateByPostId(postModel);
                    if (listImages.get(0).getSize() > 0) {
                        UploadFileUtil.updateFileByFolderId(postModelOld.getLinkImages(), listImages);
                    }
                }
                resp.sendRedirect("/personal-post?message=update_successed");
            } catch (FileUploadException e1) {
                e1.printStackTrace();
                resp.sendRedirect("/personal-post?message=update_failed");
            } catch (Exception ex) {
                ex.printStackTrace();
                resp.sendRedirect("/personal-post?message=update_failed");
            }
        } else {
            resp.sendRedirect("/home");
        }
    }
}
