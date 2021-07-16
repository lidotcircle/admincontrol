package hello.admincontrol.service.dto.auth;


public class JWTResponse {
    private String jwtToken;
    public String getJwtToken() {
        return this.jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

