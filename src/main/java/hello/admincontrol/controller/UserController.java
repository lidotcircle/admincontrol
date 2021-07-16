package hello.admincontrol.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.OperationType;
import hello.admincontrol.exception.Forbidden;
import hello.admincontrol.exception.Unauthorized;
import hello.admincontrol.service.APIAuthorizationService;
import hello.admincontrol.service.UserService;
import hello.admincontrol.service.dto.user.UserPasswordPutDTO;
import hello.admincontrol.service.dto.user.UserPostDTO;
import hello.admincontrol.service.dto.user.UserPutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "用户注册 & 个人信息")
@RestController
@RequestMapping("/apis/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private APIAuthorizationService authorizationService;

    @Operation(summary = "注册")
    @PostMapping()
    public void PostUser(@Valid @RequestBody UserPostDTO request, 
                         @RequestHeader("X-Authorization") String authorToken) //{
    {
        if (request.getPhone() != null && request.getEmail() != null) {
            throw new Forbidden("不可在注册时同时指定手机和邮箱");
        }

        if (!this.authorizationService.validateToken(authorToken, OperationType.Register, request.getPhone()) &&
            !this.authorizationService.validateToken(authorToken, OperationType.Register, request.getEmail()))
        {
            throw new Unauthorized("需要额外的认证进行访问");
        }

        this.userService.createUser(request);
    } //}

    @Operation(summary = "获取用户信息")
    @GetMapping()
    public ResponseEntity<?> getUserInfo(HttpServletRequest httpreq) //{
    {
        final String username = (String)httpreq.getAttribute("username");
        return new ResponseEntity<>(this.userService.getUserInfo(username), HttpStatus.OK);
    } //}

    @Operation(summary = "修改基本的用户信息")
    @PutMapping()
    public void putUser(HttpServletRequest httpreq, @RequestBody UserPutDTO request) //{
    {
        final String username = (String)httpreq.getAttribute("username");
        if (request.getUserName().equals(username)) {
            throw new Forbidden("无权限修改");
        }

        this.userService.updateUser(request);
    } //}

    @Operation(summary = "修改密码")
    @PutMapping("/password")
    public void putUserPassword(HttpServletRequest httpreq, @RequestBody UserPasswordPutDTO request) //{
    {
        final String username = (String)httpreq.getAttribute("username");
        if (request.getUserName().equals(username)) {
            throw new Forbidden("无权限修改");
        }

        this.userService.updateUserPassword(request);
    } //}
}

