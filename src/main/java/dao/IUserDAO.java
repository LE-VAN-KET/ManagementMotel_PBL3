package dao;

import bean.UserModel;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
    UserModel findOne(Long userId);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);
}
