package mapper.implement;

import mapper.IRowMapper;
import bean.AccountModel;
import bean.RoleModel;
import bean.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements IRowMapper<AccountModel> {
    @Override
    public AccountModel mapRow(ResultSet resultSet) {
        try {
            AccountModel account = new AccountModel();
            account.setAccountId(resultSet.getLong("accountId"));
            account.setUsername(resultSet.getString("username"));
            account.setPassword(resultSet.getString("password"));

            UserModel userModel = new UserMapper().mapRow(resultSet);
            account.setUser(userModel);

            account.setCreateAt(resultSet.getTimestamp("createAt"));
            account.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
