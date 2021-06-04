package dao;

import bean.AccountModel;

import java.util.List;

public interface IAccountDAO {
    AccountModel findByUsername(String username);
    AccountModel findByUsernameOrEmail(String userOrEmail);
    AccountModel findByEmail(String email);
    AccountModel findUsernameAndPassword(String username, String password);
    List<AccountModel> selectAll();
    Long insert(AccountModel account);
    void update(AccountModel account);
    AccountModel findByUsernameEdit(String username, Long userId);
    void updateUsername(AccountModel accountModel);
}
