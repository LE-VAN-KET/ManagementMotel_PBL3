package service.implement;

import dao.IRoleDAO;
import service.IRoleService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class RoleService implements IRoleService {
    @Inject
    private IRoleDAO roleDAO;
}
