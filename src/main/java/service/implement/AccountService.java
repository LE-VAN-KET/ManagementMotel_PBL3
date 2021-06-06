package service.implement;

import bean.AccountModel;
import bean.UserModel;
import constant.SystemConstant;
import dao.IAccountDAO;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import service.IAccountService;
import service.IUserService;

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
    private IUserService userService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    public AccountModel findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

    @Override
    public AccountModel findByUserNameAndPassword(String username, String password) {
        AccountModel accountModel = accountDAO.findByUsernameOrEmail(username);
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
            userId = userService.addUser(accountModel.getUser());
        } else {
            userId = userService.insert(accountModel.getUser());
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
        UserModel userModel = userService.findEmailUser(email);
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

            String usernameregex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
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

    @Override
    public List<String> validateEdit(AccountModel accountModelFresh) {
        List<String> errors = new ArrayList<>();
        // check all field empty
        if (isAll_Fields_Empty(accountModelFresh.getUser().getFullName(), accountModelFresh.getUser().getEmail(),
                accountModelFresh.getUser().getSDT(), accountModelFresh.getUsername())) {
            errors.add(resourceBundle.getString("all_fields_not_empty"));
        } else {
            /*
             * regex validation format fields unique
             * if field format valid
             * then check field already exist */
            String emaiRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!accountModelFresh.getUser().getEmail().matches(emaiRegex)) {
                errors.add(resourceBundle.getString("email_invalid"));
            } else {
                // validate email edit already
                UserModel userModelEmail = userService.findByEmailEdit(accountModelFresh.getUser().getEmail(),
                        accountModelFresh.getUser().getUserId());
                if (userModelEmail != null) {
                    errors.add(resourceBundle.getString("email_already_exist"));
                }
            }

            String phoneVietnameseRegex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
            if (!accountModelFresh.getUser().getSDT().matches(phoneVietnameseRegex)) {
                errors.add(resourceBundle.getString("phone_invalid"));
            } else {
                //validate phone edit already
                UserModel userModelPhone = userService.findPhonelEdit(accountModelFresh.getUser().getSDT(),
                        accountModelFresh.getUser().getUserId());
                if (userModelPhone != null) {
                    errors.add(resourceBundle.getString("phone_already_exist"));
                }
            }

            String usernameregex = "[0-9a-zA-Z_.-]*";
            if (!accountModelFresh.getUsername().matches(usernameregex)) {
                errors.add(resourceBundle.getString("username_invalid"));
            } else {
                // validate username already
                AccountModel accountModel = accountDAO.findByUsernameEdit(accountModelFresh.getUsername(),
                        accountModelFresh.getUser().getUserId());
                if (accountModel != null) {
                    errors.add(resourceBundle.getString("usename_already_exist"));
                }
            }
        }

        return errors;
    }

    @Override
    public void editProfile(AccountModel accountModel) {
        userService.update(accountModel.getUser());
        if (accountModel.getPassword() == null || "".equals(accountModel.getPassword())) {
            accountDAO.updateUsername(accountModel);
        } else {
            accountModel.setPassword(hash(accountModel.getPassword()));
            accountDAO.update(accountModel);
        }
    }

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(SystemConstant.SALT));
    }

    private boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
