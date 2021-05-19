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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/post"})
@MultipartConfig
public class PostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private IDistrictService districtService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(SystemConstant.ACCOUNTMODEL,
                SessionUtil.getInstance().getValue(req, SystemConstant.ACCOUNTMODEL));
        List<DistrictModel> districtModels = districtService.selectViewAll();
        req.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
        req.setAttribute(SystemConstant.ACCOUNTMODEL,  SessionUtil.getInstance().getValue(req,
                SystemConstant.ACCOUNTMODEL));
        req.getRequestDispatcher("/views/client/layouts/Post-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // checks if the request actually contains upload file
        if (ServletFileUpload.isMultipartContent(req))  {
            try {
                List<FileItem> formItems = null;
                Iterator iter = null;
                formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                iter = formItems.iterator();
                Long postId = null;
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
                                BeanUtils.setProperty(villageModel, item.getFieldName(), item.getString());
                            } /*else if (item.getFieldName().equals("userId")) {
                                BeanUtils.setProperty(userModel, item.getFieldName(), item.getString());
                            }*/ else {
                                BeanUtils.setProperty(postModel, item.getFieldName(), item.getString());
                            }
                        } else {
                            listImages.add(item);
                        }
                    }
                    AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                            SystemConstant.ACCOUNTMODEL);
                    userModel.setUserId(accountModel.getUser().getUserId());
                    postModel.setUserModel(userModel);
                    postModel.setVillageModel(villageModel);
                    postModel.setLinkImages("");
                    postModel.setStatusPost(false);
                    postModel.setStatusRental(false);
                    List<String> errors = postService.validatePost(postModel);
                    if (!errors.isEmpty()) {
                        req.setAttribute(SystemConstant.ERRORS, errors);
                        doGet(req, resp);
                        return;
                    }
                    postId = postService.insert(postModel);
                }
                String folderId = UploadFileUtil.uploadFile(postId.toString(), listImages);
                postService.updateLinkImages(postId, folderId);
                resp.sendRedirect("/home");
            } catch (FileUploadException e1) {
                e1.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            resp.sendRedirect("/home");
        }
    }
}
