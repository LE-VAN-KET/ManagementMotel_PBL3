package service;

import bean.PostModel;
import paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IPostService {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll(Pageble pageble);
    int getTotalItem();
    PostModel findOneByPostSlug(String postSLug);
    PostModel findByPostId(Long postId);

    void deleteByPostId(Long postId);
    void updateByPostId(PostModel postModel);
    void updateStatusPostByPostId(Long postId, boolean statusPost);

    Map<String, String> validatePost(PostModel postModel);
}
