package service;

import bean.RoleModel;

import java.util.List;

public interface IRoleService {
    RoleModel findOne(String roleName);
    List<RoleModel> findAll();
    Long insert(RoleModel roleModel);
    void update(RoleModel roleModel);
    void delete(Long roleId);
}
