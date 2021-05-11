package service.implement;

import bean.PostModel;
import dao.IPostDAO;
import service.IPostService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
public class PostService implements IPostService {
    @Inject
    private IPostDAO postDAO;

    @Override
    public Long insert(PostModel postModel) {
        Long postId = null;
//        if (isAll_Fields_Empty(postModel.getTitle(), postModel.getDesciption(), postModel.getLinkImages(),
//                postModel.getPrice(), postModel.getSquare(), postModel.getAddress(), postModel.getVillageId(),
//                postModel.getUserId(), postModel.getStatusPost(), postModel.getStatusRental())) {
//            // handle errors
//        } else {
            // callback method postDAO
            postId = postDAO.insert(postModel);
//        }
        return postId;
    }

    private boolean isAll_Fields_Empty(Object... parameters) {
        for (Object parameter: parameters) {
            if (((String)parameter == null) || ("".equals((String) parameter))) {
                return true;
            }
        }
        return false;
    }

    public void updateLinkImages(Long postId, String linkImages) {
        postDAO.updateLinkImages(postId, linkImages);
    }

    @Override
    public List<PostModel> selectAll() {
        return postDAO.selectAll();
    }
}
