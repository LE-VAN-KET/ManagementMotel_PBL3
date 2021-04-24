package service;

import bean.AccountModel;
import bean.UserModel;

import java.util.List;

public interface IAccountService {
    AccountModel findByUsername(String username);
    AccountModel findByUserNameAndPassword(String username, String password);
    Long insert(AccountModel account);
    void update(AccountModel account);
    List<String> registerValidation(AccountModel accountModel, String repeatPassword);
    String resetAccountPassword(String email);
}
