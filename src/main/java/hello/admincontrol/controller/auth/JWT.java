package hello.admincontrol.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

import hello.admincontrol.service.RefreshTokenService;
import hello.admincontrol.service.AuthService;
import hello.admincontrol.exception.Unauthorized;


@Tag(name = "JWT Credential", description = "用户身份认证")
@SecurityRequirements
@RestController()
class Jwt {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AuthService authService;

    static public class JwtResponse {
        private String jwtToken;
        public String getJwtToken() {
            return this.jwtToken;
        }
        public void setJwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
        }
    }

    @Operation(summary = "获取JSON Web Token")
    @GetMapping("/apis/auth/jwt")
    private ResponseEntity<?> getJwt(@RequestParam("refreshToken") String refreshToken) {
        var token = this.refreshTokenService.getRefreshToken(refreshToken)
                                            .orElseThrow(() -> new Unauthorized("无效的Token"));

        return new ResponseEntity<>(this.authService.JWT(token.getUserName()), HttpStatus.OK);
    }
}

