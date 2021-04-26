package mapper.implement;

import bean.RoleModel;
import mapper.IRowMapper;
import bean.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IRowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try {
            UserModel user = new UserModel();
            user.setUserId(resultSet.getLong("userId"));
            user.setFullName(resultSet.getString("fullName"));
            user.setEmail(resultSet.getString("email"));
            RoleModel roleModel = new RoleMapper().mapRow(resultSet);
            user.setRoleModel(roleModel);
            user.setCreateAt(resultSet.getTimestamp("createAt"));
            user.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
