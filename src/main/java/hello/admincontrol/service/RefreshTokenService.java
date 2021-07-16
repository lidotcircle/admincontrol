package hello.admincontrol.service;

import java.util.Optional;

import hello.admincontrol.entity.RefreshToken;


public interface RefreshTokenService {
    /** 在数据库中创建对应用户的Token */
    RefreshToken createRefreshToken(String userName);

    /** 可以根据Token获取用户信息 */
    Optional<RefreshToken> getRefreshToken(String token);

    /** 刷新Token的有效期 */
    void refreshRfreshToken(String token);

    /** 删除Token*/
    void deleteRefreshTokenByUserName(String userName);
    void deleteRefreshTokenByToken(String token);
}

