package dao;

import bean.PostModel;

import java.util.List;

public interface IPostDAO {
    List<PostModel> selectAll();
    Long insert(PostModel postModel);
    void update(PostModel postModel);
    void delete(PostModel postModel);
}
