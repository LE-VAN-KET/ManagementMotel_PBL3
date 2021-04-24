package service.implement;

import bean.UserModel;
import dao.IUserDAO;
import service.IUserService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findEmailUser(String email) {
        return userDAO.findEmailUser(email);
    }
}
