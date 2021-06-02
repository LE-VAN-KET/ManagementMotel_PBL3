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

@ManagedBean
public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;


    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<UserModel> findAll(Pageble pageble) {
        return userDAO.findAll(pageble);
    }

    @Override
    public UserModel findOne(Long userId) {
        return userDAO.findOne(userId);
    }

    @Override
    public Long insert(UserModel user) {
        return userDAO.insert(user);
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
    public int getTotalItem() {
        return userDAO.getTotalItem();
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
}
