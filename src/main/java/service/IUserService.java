package service;

import bean.UserModel;
import paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<UserModel> findAll();
    List<UserModel> findAll(Pageble pageble);
    UserModel findOne(Long userId);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);

    int getTotalItem();

    Map<String, String> validateVillage(UserModel userModel);
}
