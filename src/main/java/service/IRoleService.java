package service;

import bean.RoleModel;

import java.util.List;

public interface IRoleService {
    List<RoleModel> findAll();
    RoleModel findOne(String RoleName);
}
