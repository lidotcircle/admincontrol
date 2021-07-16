package hello.admincontrol.service;

import hello.admincontrol.OperationType;

public interface APIAuthorizationService {
    /** 签发权限 Token */
    String issueTo(String subject, OperationType operationType, int timeoutMs);

    /** 验证 Token */
    boolean validateToken(String token, OperationType OperationType, String subject);
}

