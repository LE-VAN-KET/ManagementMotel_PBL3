package dao;

import bean.RoleModel;

import java.util.List;

public interface IRoleDAO {
    List<RoleModel> findAll();
    RoleModel findOne(String roleName);
    Long insert(RoleModel roleModel);
    void update(RoleModel roleModel);
    void delete(Long roleId);
}
