package mapper.implement;

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
            user.setSDT(resultSet.getString("SDT"));
            user.setRoleId(resultSet.getLong("roleId"));
            user.setCreateAt(resultSet.getTimestamp("createAt"));
            user.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
