package service.implement;

import bean.UserModel;
import dao.IUserDAO;
import dao.implement.DistrictDAO;
import paging.Pageble;
import service.IUserService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@ManagedBean
public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;


    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<UserModel> findAll(String searchText, Pageble pageble) {
        return userDAO.findAll(searchText, pageble);
    }

    @Override
    public void update(UserModel user) {
        userDAO.update(user);
    }

    @Override
    public void delete(Long userId) {
        userDAO.delete(userId);
    }

    @Override
    public int getTotalItem(String searchText) {
        return userDAO.getTotalItem(searchText);
    }

    @Override
    public Map<String, String> validateVillage(UserModel userModel) {
        Map<String, String> errors = new HashMap<>();
        if (userModel.getFullName() == null
                || "".equals(userModel.getFullName()))
            errors.put("fullName_error", "Họ tên không được bỏ trống");

        if (userModel.getEmail() == null
                || "".equals(userModel.getEmail()))
            errors.put("email_error", "Email không được bỏ trống");

        if (userModel.getSDT() == null
                || "".equals(userModel.getSDT()))
            errors.put("SDT_error", "Số điện thoại không được bỏ trống");

        /*if(new DistrictDAO().findOne(villageModel.getDistrictModel().getDistrictId()).size() == 0)
            errors.put("districtName_error","Quận không trùng khớp với dữ liệu");*/
        return errors;
    }

    @Override
    public UserModel findEmailUser(String email) {
        return userDAO.findEmailUser(email);
    }

    @Override
    public Long addUser(UserModel userModel) {
        return userDAO.addUser(userModel);
    }

    @Override
    public Long insert(UserModel userModel) {
        return userDAO.insert(userModel);
    }

    @Override
    public UserModel findByEmailEdit(String email, Long userId) {
        return userDAO.findEmailEdit(email, userId);
    }

    @Override
    public UserModel findPhonelEdit(String phone, Long userId) {
        return userDAO.findPhonelEdit(phone, userId);
    }

    @Override
    public UserModel findOne(Long userId) {
        return userDAO.findOne(userId);
    }
}
