package hello.admincontrol.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.service.AuthService;
import hello.admincontrol.service.dto.auth.UsernamePasswordLoginPostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "登录 & 注销")
@RestController()
@RequestMapping("/apis/auth/refresh-token")
class Login {
    @Autowired
    private AuthService authService;

    @Operation(summary = "用户名登录")
    @PostMapping()
    @SecurityRequirements
    private ResponseEntity<?> login(@RequestBody @Valid UsernamePasswordLoginPostDTO req,
                                    @RequestHeader(value = "X-Authorization", required = false) String authorToken,
                                    HttpServletRequest httpreq) //{
    {
        return new ResponseEntity<>(
                this.authService.loginByUserName(req.getUserName(), req.getPassword()),
                HttpStatus.OK);
    } //}

    @Operation(summary = "注销登录获得的Token")
    @DeleteMapping()
    private void logout(@RequestParam("refreshToken") String refreshToken) {
        this.authService.logout(refreshToken);
    }
}

