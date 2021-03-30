package dao;

import bean.AccountModel;

public interface IAccountDAO {
    AccountModel findUsernameAndPassword(String username, String password);
    Long insert(AccountModel account);
    void update(AccountModel account);
}
