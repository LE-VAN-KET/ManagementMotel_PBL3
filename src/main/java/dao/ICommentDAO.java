package dao;

import bean.AccountModel;
import bean.CommentModel;

import java.util.List;

public interface ICommentDAO {
    Long insert(CommentModel commentModel);
    void update(CommentModel commentModel);
    void delete(Long commentId);
    List<CommentModel> findAll();
    List<CommentModel> findByPostId(Long postId);
    CommentModel findByCommentId(Long commentId);
}
