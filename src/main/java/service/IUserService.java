package service;

import bean.UserModel;

public interface IUserService {
    UserModel findEmailUser(String email);
}
