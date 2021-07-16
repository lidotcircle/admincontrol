package hello.admincontrol.service.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserPasswordPutDTO {
    @NotNull
    @Pattern(regexp = ".{2,}")
    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull
    private String oldPassword;
    public String getOldPassword() {
        return this.oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotNull
    @Pattern(regexp = ".{6,}")
    private String newPassword;
    public String getNewPassword() {
        return this.newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

