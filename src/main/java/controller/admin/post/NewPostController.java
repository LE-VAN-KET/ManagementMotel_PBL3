package controller.admin.post;

import bean.DistrictModel;
import bean.PostModel;
import bean.UserModel;
import bean.VillageModel;
import constant.SystemConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/new-post"})
public class NewPostController extends HttpServlet {
    @Inject
    private IPostService postService;

    @Inject
    private IDistrictService districtService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FileItem> formItems = null;
        Iterator iter = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                iter = formItems.iterator();
                Long postId = null;
                List<FileItem> listImages = new ArrayList<FileItem>();
                PostModel postModel = new PostModel();
                VillageModel villageModel = new VillageModel();
                UserModel userModel = new UserModel();

                if (formItems != null && formItems.size() > 0) {
                    while (iter.hasNext()) {
                        FileItem item = (FileItem) iter.next();
                        // processes only fields that are not form fields
                        if (item.isFormField()) {
                            if (item.getFieldName().equals("villageId")) {
                                BeanUtils.setProperty(villageModel, item.getFieldName(), item.getString("UTF-8"));
                            } /*else if (item.getFieldName().equals("userId")) {
                                BeanUtils.setProperty(userModel, item.getFieldName(), item.getString());
                            }*/ else {
                                BeanUtils.setProperty(postModel, item.getFieldName(), item.getString("UTF-8"));
                            }
                        } else {
                            listImages.add(item);
                        }
                    }
                    /*AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(req,
                            SystemConstant.ACCOUNTMODEL);*/
                    userModel.setUserId(7L);
                    postModel.setUserModel(userModel);
                    postModel.setVillageModel(villageModel);
                    postModel.setLinkImages("");
                    postModel.setStatusPost(false);
                    postModel.setStatusRental(false);
                    //validate post
                    Map<String, String> errors = postService.validatePostAdmin(postModel);
                    if (!errors.isEmpty()) {
                        request.setAttribute("errors", errors);
                        doGet(request, response);
                        return;
                    }

                    postId = postService.insert(postModel);
                }
                String folderId = UploadFileUtil.uploadFile(postId.toString(), listImages);
                postService.updateLinkImages(postId, folderId);
                response.sendRedirect("/admin/post");
            } catch (FileUploadException e1) {
                e1.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.sendRedirect("/admin/post");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DistrictModel> districtModels = districtService.selectViewAll();
        request.setAttribute(SystemConstant.DISTRICTSMODELS, districtModels);

        request.getRequestDispatcher("../views/admin/NewPost.jsp").forward(request, response);
    }
}
