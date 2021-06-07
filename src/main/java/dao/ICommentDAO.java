package dao;

import bean.CommentModel;

public interface ICommentDAO {
    Long insert(CommentModel commentModel);
    void update(CommentModel commentModel);
    void delete(Long commentId);
}
