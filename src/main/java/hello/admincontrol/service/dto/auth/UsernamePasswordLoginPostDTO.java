package hello.admincontrol.service.dto.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UsernamePasswordLoginPostDTO {
    @NotNull
    @Pattern(regexp = ".{1,}")
    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull
    @Pattern(regexp = ".{1,}")
    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

