package dao;

import bean.PostModel;
import criteria.Criteria;
import paging.Pageble;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> selectAllByStatusPost(Pageble pageble, boolean statusPost);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByCriteria(Criteria criteria, Pageble pageble);
    List<PostModel> findByUserId(Pageble pageble, Long userId);

    int getTotalItem(String searchText);
    int getTotalItemByStatusPost(boolean statusPost);
    int getTotalIemByUserId(Long userId);
    int getTotalItemByCriteria(Criteria criteria);
    PostModel findOneByPostSlug(String postSLug);
    PostModel findByPostId(Long postId);

    void deleteByPostId(Long postId);
    void updateByPostId(PostModel postModel);
    void updateStatusPostByPostId(Long postId, boolean statusPost);

    List<PostModel> selectAll(String searchText, Pageble pageble);
    void updateStatusRentalByPostId(Long postId, boolean statusRental);
    int countByPostId(Long postId);
}
