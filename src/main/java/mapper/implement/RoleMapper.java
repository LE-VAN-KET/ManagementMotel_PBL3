package mapper.implement;

import mapper.IRowMapper;
import bean.RoleModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements IRowMapper<RoleModel> {
    @Override
    public RoleModel mapRow(ResultSet resultSet) {
        try {
            RoleModel role = new RoleModel();
            role.setRoleId(resultSet.getLong("roleId"));
            if (resultSet.getString("roleName") != null) {
                role.setRoleName(resultSet.getString("roleName"));
            }
            role.setCreateAt(resultSet.getTimestamp("createAt"));
            role.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return role;
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }
}
