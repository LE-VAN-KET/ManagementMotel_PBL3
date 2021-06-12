package service;

import bean.AccountModel;
import bean.CommentModel;

import javax.inject.Named;
import java.util.List;

public interface ICommentService {
    List<String> validateComment(CommentModel commentModel);
    Long insert(CommentModel commentModel);
    void update(CommentModel commentModel);
    void delete(Long commentId);
    String authorizationUser(AccountModel accountModel);

    List<CommentModel> findAll();
    CommentModel findByCommentId(Long commentId);
    List<CommentModel> findByPostId(Long postId);
}
