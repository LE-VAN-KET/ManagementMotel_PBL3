package dao;

import bean.PostModel;
import criteria.Criteria;
import paging.Pageble;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByCriteria(Criteria criteria, Pageble pageble);

    int getTotalItem();
    int getTotalItemByCriteria(Criteria criteria);
    PostModel findOneByPostSlug(String postSLug);
}
