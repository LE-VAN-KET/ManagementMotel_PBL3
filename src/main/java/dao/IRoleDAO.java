package dao;

import bean.RoleModel;

import java.util.List;

public interface IRoleDAO {
    RoleModel findOne(String roleName);
    List<RoleModel> findAll();
    Long insert(RoleModel roleModel);
    void update(RoleModel roleModel);
    void delete(Long roleId);
}
