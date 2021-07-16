package hello.admincontrol.service.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UserPostDTO {
    @NotNull
    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = ".{8,11}")
    private String phone;
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Pattern(regexp = ".*@.*")
    private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Pattern(regexp = ".{6,}")
    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

