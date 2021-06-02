package dao;

import bean.UserModel;
import paging.Pageble;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
    List<UserModel> findAll(Pageble pageble);
    UserModel findOne(Long userId);
    Long insert(UserModel user);
    void update(UserModel user);
    void delete(Long userId);
    int getTotalItem();
}
