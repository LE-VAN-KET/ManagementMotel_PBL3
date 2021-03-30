package service.implement;

import dao.IUserDAO;
import service.IUserService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;
}
