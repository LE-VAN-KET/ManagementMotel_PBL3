package dao.implement;

import mapper.implement.RoleMapper;
import bean.RoleModel;
import dao.IRoleDAO;

import java.util.List;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {
    @Override
    public RoleModel findOne(String roleName) {
        String sql = "SELECT * FROM role WHERE roleName = ?";
        List<RoleModel> roles = query(sql, new RoleMapper(), roleName);
        return roles.isEmpty() ? null : roles.get(0);
    }
}
