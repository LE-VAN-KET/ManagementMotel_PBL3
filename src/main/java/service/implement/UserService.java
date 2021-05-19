package service.implement;

import bean.UserModel;
import dao.IUserDAO;
import service.IUserService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findEmailUser(String email) {
        return userDAO.findEmailUser(email);
    }

    @Override
    public Long addUser(UserModel userModel) {
        return userDAO.addUser(userModel);
    }

    @Override
    public Long insert(UserModel userModel) {
        return userDAO.insert(userModel);
    }

    @Override
    public void update(UserModel userModel) {
        userDAO.update(userModel);
    }

    @Override
    public UserModel findByEmailEdit(String email, Long userId) {
        return userDAO.findEmailEdit(email, userId);
    }

    @Override
    public UserModel findPhonelEdit(String phone, Long userId) {
        return userDAO.findPhonelEdit(phone, userId);
    }
}
