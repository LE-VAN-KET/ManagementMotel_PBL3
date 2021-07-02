package dao.implement;

import mapper.implement.UserMapper;
import bean.UserModel;
import dao.IUserDAO;
import paging.Pageble;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    private static UserDAO instance;
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public List<UserModel> findAll() {
        StringBuffer sql = new StringBuffer("SELECT * FROM users");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        return query(sql.toString(), new UserMapper());
    }

    public List<UserModel> findAll(String searchText, Pageble pageble) {
        StringBuffer sql = new StringBuffer("SELECT * FROM users");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        if(searchText != null && searchText != "") {
            sql.append(" where fullName like N? or SDT like ?");
            sql.append(" or email like ? or roleName like ?");
            sql.append(" LIMIT ?, ?");
            return query(sql.toString(), new UserMapper(),
                    "%" + searchText + "%", "%" + searchText + "%",
                    "%" + searchText + "%", "%" + searchText + "%",
                    pageble.getOffset(), pageble.getMaxPageItem());
        }
        sql.append(" LIMIT ?, ?");
        return query(sql.toString(), new UserMapper(), pageble.getOffset(), pageble.getMaxPageItem());
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
                user.getRoleModel().getRoleId());
    }

    @Override
    public Long addUser(UserModel userModel) {
        String sql = "INSERT INTO users(email, roleId) VALUES(?, ?)";
        return insert(sql, userModel.getEmail(), userModel.getRoleModel().getRoleId());
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

    public int getTotalItem(String searchText) {
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM users");
        sql.append(" INNER JOIN role ON users.roleId = role.roleId");
        if(searchText != null && searchText != "") {
            sql.append(" where fullName like N? or SDT like ?");
            sql.append(" or email like ? or roleName like ?");
            return count(sql.toString(), "%" + searchText + "%",
                    "%" + searchText + "%","%" + searchText + "%","%" + searchText + "%");
        }
        return count(sql.toString());
    }

    public static void main(String[] args) {
        Pageble pageble = new Pageble();
        pageble.setMaxPageItem(10);
        pageble.setPage(1);
        List<UserModel> userModels = new UserDAO().findAll("KHÁNH VY",pageble);
        userModels.forEach(System.out::println);
    }
}
