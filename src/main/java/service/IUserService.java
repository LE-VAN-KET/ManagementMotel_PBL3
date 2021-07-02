package service;

import bean.UserModel;
import paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<UserModel> findAll();
    List<UserModel> findAll(String searchText, Pageble pageble);
    UserModel findOne(Long userId);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);

    int getTotalItem(String searchText);

    Map<String, String> validateVillage(UserModel userModel);
    UserModel findEmailUser(String email);
    Long addUser(UserModel userModel);
    UserModel findByEmailEdit(String email, Long userId);
    UserModel findPhonelEdit(String phone, Long userId);
}
