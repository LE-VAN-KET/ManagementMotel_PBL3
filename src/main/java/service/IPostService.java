package service;

import bean.PostModel;
import paging.Pageble;

import java.util.List;

public interface IPostService {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    List<PostModel> findByVillageId(Long villageId, Pageble pageble);
    List<PostModel> findByParameters(String villageId, String square, String price, Pageble pageble);
    int getTotalItem();
}
