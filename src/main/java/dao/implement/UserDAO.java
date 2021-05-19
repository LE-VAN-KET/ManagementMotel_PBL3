package dao.implement;

import bean.UserModel;
import dao.IUserDAO;
import mapper.implement.UserMapper;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users INNER JOIN role ON users.roleId = role.roleId";
        return query(sql, new UserMapper());
    }

    @Override
    public UserModel findOne(Long userId) {
        String sql = "SELECT * FROM users INNER JOIN role ON users.roleId = role.roleId WHERE userId = ? LIMIT 1";
        List<UserModel> users = query(sql, new UserMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public UserModel findEmailUser(String email) {
        String sql = "SELECT * FROM users INNER JOIN role ON users.roleId = role.roleId WHERE email = ? LIMIT 1";
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
        String sql = "UPDATE users SET fullName = ?, email = ?, SDT = ? WHERE userId = ?";
        update(sql, user.getFullName(), user.getEmail(), user.getSDT(), user.getUserId());
    }

    @Override
    public void delete(Long userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        delete(sql, userId);
    }

    @Override
    public UserModel findEmailEdit(String email, Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users INNER JOIN role ON");
        sql.append(" users.roleId = role.roleId WHERE email = ? AND users.userId <> ? LIMIT 1");
        List<UserModel> users = query(sql.toString(), new UserMapper(), email, userId);
        return users.isEmpty() ? null: users.get(0);
    }

    @Override
    public UserModel findPhonelEdit(String phone, Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users INNER JOIN role ON");
        sql.append(" users.roleId = role.roleId WHERE SDT = ? AND users.userId <> ? LIMIT 1");
        List<UserModel> users = query(sql.toString(), new UserMapper(), phone, userId);
        return users.isEmpty() ? null: users.get(0);
    }
}
