package service;

import bean.PostModel;
import criteria.Criteria;
import paging.Pageble;

import java.util.List;

public interface IPostService {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> selectAllByStatusPost(Pageble pageble, boolean statusPost);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByCriteria(Criteria criteria, Pageble pageble);
    int getTotalItem();
    int getTotalItemByStatusPost(boolean statusPost);
    int getTotalIemByUserId(Long userId);
    List<String> validatePost(PostModel postModel);
    PostModel findOneByPostSlug(String postSlug);
    List<PostModel> findByUserId(Pageble pageble, Long userId);
    PostModel findByPostId(Long postId);

    void deleteByPostId(Long postId);
    void updateByPostId(PostModel postModel);
    void updateStatusRentalByPostId(Long postId, boolean statusRental);
}
