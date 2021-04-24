package bean;

public class UserModel extends AbstractModel {
    private Long userId;
    private String fullName;
    private String email;
    private String SDT;
    private RoleModel roleMole;

    public RoleModel getRoleMole() {
        return roleMole;
    }

    public void setRoleMole(RoleModel roleMole) {
        this.roleMole = roleMole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
