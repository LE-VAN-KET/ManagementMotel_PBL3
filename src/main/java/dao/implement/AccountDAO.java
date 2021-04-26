package dao.implement;

import mapper.implement.AccountMapper;
import bean.AccountModel;
import dao.IAccountDAO;

import java.util.List;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccountDAO {
    @Override
    public AccountModel findUsernameAndPassword(String username, String password) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account AS acc");
        sql.append(" INNER JOIN users AS u ON u.userId = acc.userId");
        sql.append(" INNER JOIN role AS r ON r.roleId = u.roleId");
//        sql.append(" WHERE username = ? AND password = ?");
        List<AccountModel> accounts = query(sql.toString(), new AccountMapper());
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Long insert(AccountModel account) {
        String sql = "INSERT INTO account(username, password) VALUES(?, ?)";
        return insert(sql, account.getUsername(), account.getPassword());
    }

    @Override
    public void update(AccountModel account) {
        String sql = "UPDATE account SET username = ?, password = ?";
        update(sql, account.getUsername(), account.getPassword());
    }

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        System.out.println(accountDAO.findUsernameAndPassword("",""));
    }
}
