package dao.implement;

import mapper.implement.AccountMapper;
import bean.AccountModel;
import dao.IAccountDAO;

import java.util.List;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccountDAO {
    @Override
    public AccountModel findByUsername(String username) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account AS acc");
        sql.append(" INNER JOIN users AS u ON u.userId = acc.userId");
        sql.append(" INNER JOIN role AS r ON r.roleId = u.roleId");
        sql.append(" WHERE username = ? LIMIT 1");
        List<AccountModel> accounts = query(sql.toString(), new AccountMapper(), username);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public AccountModel findByUsernameOrEmail(String userOrEmail) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account AS acc");
        sql.append(" INNER JOIN users AS u ON u.userId = acc.userId");
        sql.append(" INNER JOIN role AS r ON r.roleId = u.roleId");
        sql.append(" WHERE username = ? OR u.email = ? LIMIT 1");
        List<AccountModel> accounts = query(sql.toString(), new AccountMapper(), userOrEmail, userOrEmail);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public AccountModel findByEmail(String email) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account AS acc");
        sql.append(" INNER JOIN users AS u ON u.userId = acc.userId");
        sql.append(" INNER JOIN role AS r ON r.roleId = u.roleId");
        sql.append(" WHERE u.email = ? LIMIT 1");
        List<AccountModel> accounts = query(sql.toString(), new AccountMapper(), email);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Long insert(AccountModel account) {
        String sql = "INSERT INTO account(userId, username, password) VALUES(?, ?, ?)";
        return insert(sql, account.getUser().getUserId(), account.getUsername(), account.getPassword());
    }

    @Override
    public void update(AccountModel account) {
        String sql = "UPDATE account SET username = ?, password = ? WHERE accountId = ?";
        update(sql, account.getUsername(), account.getPassword(), account.getAccountId());
    }

    @Override
    public AccountModel findByUsernameEdit(String username, Long userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account AS acc");
        sql.append(" INNER JOIN users AS u ON u.userId = acc.userId");
        sql.append(" INNER JOIN role AS r ON r.roleId = u.roleId");
        sql.append(" WHERE acc.username = ? AND u.userId <> ?  LIMIT 1");
        List<AccountModel> accounts = query(sql.toString(), new AccountMapper(), username, userId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public void updateUsername(AccountModel accountModel) {
        String sql = "UPDATE account SET username = ? WHERE accountId = ?";
        update(sql, accountModel.getUsername(), accountModel.getAccountId());
    }
}
