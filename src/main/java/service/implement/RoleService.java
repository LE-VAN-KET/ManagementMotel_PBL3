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
    public RoleModel findOne(String RoleName) {
        return roleDAO.findOne(RoleName);
    }

    @Override
    public List<RoleModel> findAll() {
        return roleDAO.findAll();
    }
}
