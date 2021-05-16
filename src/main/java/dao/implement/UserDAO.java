package dao.implement;

import bean.UserModel;
import dao.IUserDAO;
import mapper.implement.UserMapper;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        return query(sql, new UserMapper());
    }

    @Override
    public UserModel findOne(Long userId) {
        String sql = "SELECT * FROM users WHERE userId = ? LIMIT 1";
        List<UserModel> users = query(sql, new UserMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public UserModel findEmailUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ? LIMIT 1";
        List<UserModel> users = query(sql, new UserMapper(), email);
        return users.isEmpty() ? null: users.get(0);
    }

    @Override
    public Long insert(UserModel user) {
        String sql = "INSERT INTO users(fullName, email, SDT, roleId) VALUES(?, ?, ?, ?)";
        return insert(sql, user.getFullName(), user.getEmail(), user.getSDT(),
                user.getRoleMole().getRoleId());
    }

    @Override
    public Long addUser(UserModel userModel) {
        String sql = "INSERT INTO users(email, roleId) VALUES(?, ?)";
        return insert(sql, userModel.getEmail(), userModel.getRoleMole().getRoleId());
    }

    @Override
    public void update(UserModel user) {
        String sql = "UPDATE users SET fullName = ?, email = ?, SDT = ?";
        update(sql, user.getFullName(), user.getEmail(), user.getSDT());
    }

    @Override
    public void delete(Long userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        delete(sql, userId);
    }
}
