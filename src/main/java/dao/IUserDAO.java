package dao;

import bean.UserModel;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
    UserModel findOne(Long userId);
    UserModel findEmailUser(String email);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);
    Long addUser(UserModel userModel);
    UserModel findEmailEdit(String email, Long userId);
    UserModel findPhonelEdit(String phone, Long userId);
}
