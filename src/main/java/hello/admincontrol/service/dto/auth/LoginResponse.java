package hello.admincontrol.service.dto.auth;


public class LoginResponse {
    private String refreshToken;
    public String getRefreshToken() {
        return this.refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
