package controller.admin.post;

import bean.*;
import com.google.api.services.drive.model.File;
import constant.SystemConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.IDistrictService;
import service.IPostService;
import service.IVillageService;
import utils.UploadFileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/edit-post/*"})
public class EditPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // checks if the request actually contains upload file
        if (ServletFileUpload.isMultipartContent(request))  {
            try {
                Long postId = Long.parseLong(request.getPathInfo().substring(1));
                PostModel postModelOld = postService.findByPostId(postId);
               /* AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                        SystemConstant.ACCOUNTMODEL);*/
                if ((postModelOld == null)/* || (postModelOld.getUserModel().getUserId() !=
                        accountModel.getUser().getUserId())*/) {
                    // error post not exist or user not permission
                    response.sendRedirect("/admin/home?message=not_permission&&alert=danger");
                    return;
                }
                List<FileItem> formItems = null;
                Iterator iter = null;
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                upload.setHeaderEncoding("UTF-8");
                formItems = upload.parseRequest(request);
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
                    userModel.setUserId(7L/*accountModel.getUser().getUserId()*/);
                    postModel.setUserModel(userModel);
                    postModel.setVillageModel(villageModel);
                    postModel.setLinkImages(postModelOld.getLinkImages());
                    postModel.setStatusPost(postModelOld.getStatusPost());
                    postModel.setStatusRental(postModelOld.getStatusRental());
                    /*List<String> errors = postService.validatePost(postModel);
                    if (!errors.isEmpty()) {
                        request.setAttribute(SystemConstant.ERRORS, errors);
                        doGet(request, response);
                        return;
                    }*/
                    postService.updateByPostId(postModel);
                    if (listImages.get(0).getSize() > 0) {
                        UploadFileUtil.updateFileByFolderId(postModelOld.getLinkImages(), listImages);
                    }
                }
                response.sendRedirect("/admin/post?message=update_successed");
            } catch (FileUploadException e1) {
                e1.printStackTrace();
                response.sendRedirect("/admin/post?message=update_failed");
            } catch (Exception ex) {
                ex.printStackTrace();
                response.sendRedirect("/admin/post?message=update_failed");
            }
        } else {
            response.sendRedirect("/admin/post");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postSlug = request.getPathInfo().substring(1);
        System.out.println(postSlug);
        try {
            PostModel postModel = postService.findOneByPostSlug(postSlug);
            if (postModel != null) {
               /* AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request,
                        SystemConstant.ACCOUNTMODEL);
                if (postModel.getUserModel().getUserId() != accountModel.getUser().getUserId()) {
                    response.sendRedirect("/home?message=not_permission&&alert=danger");
                }*/
                request.setAttribute(SystemConstant.POSTMODEL, postModel);
                request.setAttribute(SystemConstant.IMAGES, UploadFileUtil.getLinkImagesByFolderId(postModel.getLinkImages()));
                /*request.setAttribute(SystemConstant.ACCOUNTMODEL, accountModel);*/
                List<DistrictModel> districtModels = districtService.selectViewAll();
                request.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);
                request.getRequestDispatcher("/views/admin/EditPost.jsp").forward(request, response);
            } else {
                response.sendRedirect("home?message=not_permission&&alert=danger");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/home?message=not_permission&&alert=danger");
        }
    }
}
