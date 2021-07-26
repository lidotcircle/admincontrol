package hello.admincontrol.controller.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.admincontrol.OperationType;
import hello.admincontrol.exception.NotFound;
import hello.admincontrol.service.APIAuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "API访问授权令牌")
@RestController
@RequestMapping("/apis/auth-token")
public class APIAuthController {
    static Map<String, OperationType> operationMap = new HashMap<>();
    static {
        operationMap.put("register",      OperationType.Register);
        operationMap.put("login",         OperationType.Login);
        operationMap.put("message",       OperationType.Message);
        operationMap.put("resetPassword", OperationType.ResetPassword);
    }

    @Autowired
    private APIAuthorizationService authTokenService;

    @Operation(summary = "获取任意的API访问令牌, 仅用于测试")
    @GetMapping("/magic")
    private ResponseEntity<?> magicAllAuth(
            @RequestParam() String operation, 
            @RequestParam(required = false) String subject, 
            HttpServletRequest httpreq) {
        final var op = operationMap.get(operation);
        if (op == null) {
            throw new NotFound("请求类型非法");
        }
        if(subject == null) {
            subject = (String)httpreq.getAttribute("username");
        }
        if(subject == null) {
            subject = "dummy";
        }
        final var token = this.authTokenService.issueTo(subject, op, 1000 * 60, 1);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}

