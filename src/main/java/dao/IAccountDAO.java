package dao;

import bean.AccountModel;

import java.util.List;

public interface IAccountDAO {
    AccountModel findUsernameAndPassword(String username, String password);
    List<AccountModel> selectAll();
    Long insert(AccountModel account);
    void update(AccountModel account);
}
