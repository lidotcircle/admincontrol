package hello.admincontrol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hello.admincontrol.config.JWTUtil;
import hello.admincontrol.exception.NotFound;
import hello.admincontrol.exception.Unauthorized;
import hello.admincontrol.repository.UserRepository;
import hello.admincontrol.service.AuthService;
import hello.admincontrol.service.RefreshTokenService;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RefreshTokenService tokenService;
    @Autowired
    private UserRepository userResp;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwt;

	@Override
	public String loginByUserName(String username, String password) {
        final var user = this.userResp.findByUserName(username)
            .orElseThrow(() -> new NotFound(String.format("用户 %s 不存在", username)));

        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new Unauthorized("密码错误");
        }

        return this.tokenService.createRefreshToken(username).getToken();
	}

	@Override
	public void logout(String token) {
        this.tokenService.deleteRefreshTokenByToken(token);
	}

	@Override
	public String JWT(String username) {
        return this.jwt.generateToken(username);
	}
    
}

