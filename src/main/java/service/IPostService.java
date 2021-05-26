package service;

import bean.PostModel;
import criteria.Criteria;
import paging.Pageble;

import java.util.List;

public interface IPostService {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByCriteria(Criteria criteria, Pageble pageble);
    int getTotalItem();
    List<String> validatePost(PostModel postModel);
    PostModel findOneByPostSlug(String postSlug);
}
