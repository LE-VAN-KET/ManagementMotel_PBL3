package service.implement;

import bean.AccountModel;
import bean.UserModel;
import constant.SystemConstant;
import dao.IAccountDAO;
import dao.IUserDAO;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import service.IAccountService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
public class AccountService implements IAccountService {
    @Inject
    private IAccountDAO accountDAO;

    @Inject
    private IUserDAO userDAO;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    public AccountModel findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

    @Override
    public AccountModel findByUserNameAndPassword(String username, String password) {
        AccountModel accountModel = findByUsername(username);
        if (accountModel != null) {
            boolean verifyPass = verifyHash(password, accountModel.getPassword());
            if (verifyPass) {
                return  accountModel;
            }
        }
        return null;
    }

    @Override
    public Long insert(AccountModel accountModel) {
        Long userId = null;
        if ((accountModel.getUser().getFullName() == null) && (accountModel.getUser().getSDT() == null)
            || ("".equals(accountModel.getUser().getFullName())) || ("".equals(accountModel.getUser().getSDT()))) {
            userId = userDAO.addUser(accountModel.getUser());
        } else {
            userId = userDAO.insert(accountModel.getUser());
        }

        accountModel.setPassword(hash(accountModel.getPassword()));

        if (userId != null) {
            accountModel.getUser().setUserId(userId);
            return accountDAO.insert(accountModel);
        }
        return  null;
    }

    @Override
    public void update(AccountModel account) {
        accountDAO.update(account);
    }

    private boolean isEmail(String email) {
        UserModel userModel = userDAO.findEmailUser(email);
        return (userModel != null) ? true: false;
    }

    private boolean isAll_Fields_Empty(Object... parameters) {
        for (Object parameter: parameters) {
            if (((String)parameter == null) || ("".equals((String) parameter))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> registerValidation(AccountModel accountModel, String repeatPassword) {
        List<String> errors = new ArrayList<>();
        // validation all fields not empty
        if (isAll_Fields_Empty(accountModel.getUsername(), accountModel.getPassword(),
                repeatPassword, accountModel.getUser().getEmail())) {
            errors.add(resourceBundle.getString("all_fields_not_empty"));
        } else {
            // compare repeat password and password invalid
            if (!accountModel.getPassword().equals(repeatPassword)) {
                errors.add(resourceBundle.getString("repeat_password_invalid"));
            }

            String usernameregex = "[0-9a-zA-Z_.-]*";
            if (!accountModel.getUsername().matches(usernameregex)) {
                errors.add(resourceBundle.getString("username_invalid"));
            }

            // regex validation format email
            String emaiRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!accountModel.getUser().getEmail().matches(emaiRegex)) {
                errors.add(resourceBundle.getString("email_invalid"));
            }

            // check email exist
            if (isEmail(accountModel.getUser().getEmail())) {
                errors.add(resourceBundle.getString("email_already_exist"));
            }

            // check username already exist
            AccountModel account = findByUsername(accountModel.getUsername());
            if (account != null) {
                errors.add(resourceBundle.getString("usename_already_exist"));
            }
        }

        return  errors;
    }

    @Override
    public String resetAccountPassword(String email) {
        String randomPassword = RandomStringUtils.randomAlphanumeric(10);
        AccountModel accountModel = accountDAO.findByEmail(email);
        if (accountModel != null) {
            accountModel.setPassword(hash(randomPassword));
            accountDAO.update(accountModel);
            return randomPassword;
        }
        return null;
    }

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(SystemConstant.SALT));
    }

    private boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
