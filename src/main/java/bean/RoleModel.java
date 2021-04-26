package bean;

import java.util.Collection;

public class RoleModel extends AbstractModel {
    private Long roleId;
    private String roleName;
    private Collection<UserModel> userModels;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(Collection<UserModel> userModels) {
        this.userModels = userModels;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
