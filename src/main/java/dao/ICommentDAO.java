package dao;

import bean.AccountModel;
import bean.CommentModel;

public interface ICommentDAO {
    Long insert(CommentModel commentModel);
    void update(CommentModel commentModel);
    void delete(Long commentId);
}
