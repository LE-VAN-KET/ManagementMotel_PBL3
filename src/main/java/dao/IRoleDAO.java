package dao;

import bean.RoleModel;

import java.util.List;

public interface IRoleDAO {
    List<RoleModel> findAll();
    RoleModel findOne(String roleName);
}
