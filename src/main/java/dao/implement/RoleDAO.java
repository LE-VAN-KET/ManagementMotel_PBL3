package dao.implement;

import mapper.implement.RoleMapper;
import bean.RoleModel;
import dao.IRoleDAO;
import mapper.implement.UserMapper;

import java.util.List;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {
    @Override
    public RoleModel findOne(String roleName) {
        String sql = "SELECT * FROM role WHERE roleName = ?";
        List<RoleModel> roles = query(sql, new RoleMapper(), roleName);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public List<RoleModel> findAll() {
        String sql = "SELECT * FROM role";
        return query(sql, new RoleMapper());
    }

    @Override
    public Long insert(RoleModel roleModel) {
        String sql = "INSERT INTO role(roleName) VALUES(?)";
        return insert(sql, roleModel.getRoleName());
    }

    @Override
    public void update(RoleModel roleModel) {
        String sql = "UPDATE role SET roleName = ?";
        update(sql, roleModel.getRoleName());
    }

    @Override
    public void delete(Long roleId) {
        String sql = "DELETE FROM role WHERE roleId = ?";
        delete(sql, roleId);
    }

}
