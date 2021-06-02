package service;

import bean.AccountModel;

import java.util.List;

public interface IAccountService {
    AccountModel findUsernameAndPassword(String username, String password);
    List<AccountModel> selectAll();
    Long insert(AccountModel account);
    void update(AccountModel account);
}
