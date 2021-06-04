package dao;

import bean.UserModel;
import paging.Pageble;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
    List<UserModel> findAll(String searchText, Pageble pageble);
    UserModel findOne(Long userId);
    UserModel findEmailUser(String email);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);
    int getTotalItem(String searchText);
    Long addUser(UserModel userModel);
    UserModel findEmailEdit(String email, Long userId);
    UserModel findPhonelEdit(String phone, Long userId);
}
