package dao;

import bean.AccountModel;

public interface IAccountDAO {
    AccountModel findByUsername(String username);
    AccountModel findByEmail(String email);
    Long insert(AccountModel account);
    void update(AccountModel account);
}
