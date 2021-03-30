package service;

import bean.AccountModel;

public interface IAccountService {
    AccountModel findUsernameAndPassword(String username, String password);
    Long insert(AccountModel account);
    void update(AccountModel account);
}
