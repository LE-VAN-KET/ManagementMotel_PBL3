package mapper.implement;

import bean.PostModel;
import bean.RoleModel;
import bean.UserModel;
import mapper.IRowMapper;
import bean.CommentModel;

import java.sql.ResultSet;

public class CommentMapper implements IRowMapper<CommentModel> {
    @Override
    public CommentModel mapRow(ResultSet resultSet) {
        CommentModel commentModel = new CommentModel();
        try {
            commentModel.setCommentId(resultSet.getLong("comment.commentId"));
            commentModel.setContent(resultSet.getString("comment.content"));
            PostModel postModel = new PostMapper().mapRow(resultSet);
            commentModel.setPostModel(postModel);
//            UserModel userModel = new UserMapper().mapRow(resultSet);
            // map usermodel create comment
            UserModel userModel = new UserModel();
            userModel.setUserId(resultSet.getLong("comment_u.userId"));
            userModel.setFullName(resultSet.getString("comment_u.fullName"));
            userModel.setEmail(resultSet.getString("comment_u.email"));
            userModel.setSDT(resultSet.getString("comment_u.SDT"));
            userModel.setCreateAt(resultSet.getTimestamp("comment_u.createAt"));
            userModel.setModifiedAt(resultSet.getTimestamp("comment_u.modifiedAt"));
            // map role user create comment
            RoleModel role = new RoleModel();
            role.setRoleId(resultSet.getLong("role_comment_u.roleId"));
            role.setRoleName(resultSet.getString("role_comment_u.roleName"));
            role.setCreateAt(resultSet.getTimestamp("role_comment_u.createAt"));
            role.setModifiedAt(resultSet.getTimestamp("role_comment_u.modifiedAt"));
            userModel.setRoleModel(role);
            commentModel.setUserModel(userModel);
            commentModel.setCreateAt(resultSet.getTimestamp("comment.createAt"));
            commentModel.setModifiedAt(resultSet.getTimestamp("comment.modifiedAt"));
            return commentModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
