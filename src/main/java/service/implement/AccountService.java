package service.implement;

import bean.AccountModel;
import dao.IAccountDAO;
import service.IAccountService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class AccountService implements IAccountService {
    @Inject
    private IAccountDAO accountDAO;

    @Override
    public AccountModel findUsernameAndPassword(String username, String password) {
        return accountDAO.findUsernameAndPassword(username, password);
    }

    @Override
    public Long insert(AccountModel account) {
        return accountDAO.insert(account);
    }

    @Override
    public void update(AccountModel account) {
        accountDAO.update(account);
    }
}
