package hello.admincontrol.service;

import hello.admincontrol.service.dto.auth.JWTResponse;


public interface AuthService {
    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return refresh token
     */
    String loginByUserName(String username, String password);

    /**
     * 登出, 删除 refresh token
     * @param token refresh token
     */
    void logout(String token);

    /**
     * 获取 JSON Web Token 用于身份凭证
     * @param username JWT中的subject
     * @return JWT
     */
    String JWT(String username);
}

