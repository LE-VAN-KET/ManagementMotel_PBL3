package dao;

import bean.PostModel;
import paging.Pageble;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    int getTotalItem();
    PostModel findOneByPostSlug(String postSLug);
    PostModel findByPostId(Long postId);

    void deleteByPostId(Long postId);
    void updateByPostId(PostModel postModel);
    void updateStatusPostByPostId(Long postId, boolean statusPost);
}
