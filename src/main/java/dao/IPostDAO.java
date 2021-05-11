package dao;

import bean.PostModel;

import java.util.List;

public interface IPostDAO {
    Long insert(PostModel postModel);
    void updateLinkImages(Long postId, String linkImages);
    List<PostModel> selectAll();
}
