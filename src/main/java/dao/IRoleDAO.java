package dao;

import bean.RoleModel;

public interface IRoleDAO {
    RoleModel findOne(String roleName);
}
