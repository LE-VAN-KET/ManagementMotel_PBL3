package service;

import bean.PostModel;

import java.util.List;

public interface IPostService {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll();
}
