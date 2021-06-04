package service.implement;

import bean.RoleModel;
import dao.IRoleDAO;
import service.IRoleService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.management.relation.Role;
import java.util.List;

@ManagedBean
public class RoleService implements IRoleService {
    @Inject
    private IRoleDAO roleDAO;

    @Override
    public RoleModel findOne(String roleName) {
        return roleDAO.findOne(roleName);
    }

    @Override
    public List<RoleModel> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Long insert(RoleModel roleModel) {
        return roleDAO.insert(roleModel);
    }

    @Override
    public void update(RoleModel roleModel) {
        update(roleModel);
    }

    @Override
    public void delete(Long roleId) {
        delete(roleId);
    }
}
