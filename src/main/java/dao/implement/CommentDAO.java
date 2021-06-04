package dao.implement;

import bean.AccountModel;
import bean.CommentModel;
import constant.SystemConstant;
import dao.ICommentDAO;
import utils.SessionUtil;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {
    @Override
    public Long insert(CommentModel commentModel) {
        String sql = "INSERT INTO comment(content, postId, userId) VALUES(?, ?, ?)";
        return insert(sql, commentModel.getContent(), commentModel.getPostId(), commentModel.getUserId());
    }

    @Override
    public void update(CommentModel commentModel) {
        String sql = "update comment set content = ? where commentId = ?";
        update(sql, commentModel.getContent(), commentModel.getCommentId());
    }

    @Override
    public void delete(Long commentId) {
        String sql = "delete from comment where commentId = ?";
        delete(sql, commentId);
    }
}
