package hello.admincontrol.service.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserPutDTO {
    @NotNull
    @Pattern(regexp = ".{2,}")
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

    private String profilePhoto;
    public String getProfilePhoto() {
        return this.profilePhoto;
    }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

