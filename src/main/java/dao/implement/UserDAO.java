package dao.implement;

import mapper.implement.UserMapper;
import bean.UserModel;
import dao.IUserDAO;
import paging.Pageble;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public List<UserModel> findAll() {
        StringBuffer sql = new StringBuffer("SELECT * FROM users");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        return query(sql.toString(), new UserMapper());
    }

    public List<UserModel> findAll(Pageble pageble) {
        StringBuffer sql = new StringBuffer("SELECT * FROM users");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(), new UserMapper(), pageble.getOffset(), pageble.getMaxPageItem());
    }

    @Override
    public UserModel findOne(Long userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        List<UserModel> users = query(sql, new UserMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Long insert(UserModel user) {
        String sql = "INSERT INTO users(fullName, email, SDT, roleId) VALUES(?, ?, ?, ?)";
        return insert(sql, user.getFullName(), user.getEmail(), user.getSDT(),user.getRoleModel().getRoleId());
    }

    @Override
    public void update(UserModel user) {
        StringBuilder sql = new StringBuilder("UPDATE users");
        sql.append(" SET fullName = ?, email = ?, SDT = ?, roleId = ?");
        sql.append(" WHERE userId = ?");
        update(sql.toString(), user.getFullName(), user.getEmail(),
                user.getSDT(),user.getRoleModel().getRoleId(),
                user.getUserId());
    }

    @Override
    public void delete(Long userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        delete(sql, userId);
    }

    public int getTotalItem() {
        String sql = "SELECT count(*) FROM users";
        return count(sql);
    }
}
