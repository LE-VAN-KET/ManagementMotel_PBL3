package dao;

import bean.AccountModel;

public interface IAccountDAO {
    AccountModel findByUsername(String username);
    AccountModel findByUsernameOrEmail(String userOrEmail);
    AccountModel findByEmail(String email);
    Long insert(AccountModel account);
    void update(AccountModel account);
    AccountModel findByUsernameEdit(String username, Long userId);
    void updateUsername(AccountModel accountModel);
}
