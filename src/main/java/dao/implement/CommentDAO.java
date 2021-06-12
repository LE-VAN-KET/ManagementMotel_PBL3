package dao.implement;

import bean.CommentModel;
import dao.ICommentDAO;
import mapper.implement.CommentMapper;

import java.util.List;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {
    private static CommentDAO instance;
    public static CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    private StringBuilder sqlQuery() {
        StringBuilder sql = new StringBuilder("select comment.commentId, comment.content, comment.createAt,");
        sql.append(" comment.modifiedAt, post.*, post_u.*, role_post_u.*, village.*, district.*, comment_u.*,");
        sql.append(" role_comment_u.*  from comment INNER JOIN post ON post.postId = comment.postId");
        sql.append(" INNER JOIN users AS post_u ON post.userId = post_u.userId");
        sql.append(" INNER JOIN role AS role_post_u ON post_u.roleId = role_post_u.roleId");
        sql.append(" INNER JOIN village ON post.villageId = village.villageId");
        sql.append(" INNER JOIN district ON village.districtId = district.districtId");
        sql.append(" INNER JOIN users AS comment_u ON comment.userId = comment_u.userId");
        sql.append(" INNER JOIN role AS role_comment_u ON comment_u.roleId = role_comment_u.roleId");
        return sql;
    }

    @Override
    public Long insert(CommentModel commentModel) {
        String sql = "INSERT INTO comment(content, postId, userId) VALUES(?, ?, ?)";
        return insert(sql, commentModel.getContent(), commentModel.getPostModel().getPostId(),
                commentModel.getUserModel().getUserId());
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

    @Override
    public List<CommentModel> findAll() {
        StringBuilder sql = sqlQuery();
        sql.append(" ORDER BY comment.createAt");
        return query(sql.toString(), new CommentMapper());
    }

    @Override
    public CommentModel findByCommentId(Long commentId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where comment.commentId = ?");
        List<CommentModel> commentModels = query(sql.toString(), new CommentMapper(), commentId);
        return commentModels.isEmpty() ? null: commentModels.get(0);
    }

    @Override
    public List<CommentModel> findByPostId(Long postId) {
        StringBuilder sql = sqlQuery();
        sql.append(" where comment.postId = ? ORDER BY comment.createAt");
        return query(sql.toString(), new CommentMapper(), postId);
    }
}
