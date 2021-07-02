package service;

import bean.UserModel;

import java.util.List;

public interface IUserService {
    UserModel findEmailUser(String email);
    Long addUser(UserModel userModel);
    Long insert(UserModel userModel);
    void update(UserModel userModel);
    UserModel findByEmailEdit(String email, Long userId);
    UserModel findPhonelEdit(String phone, Long userId);
    UserModel findOne(Long userId);
}
