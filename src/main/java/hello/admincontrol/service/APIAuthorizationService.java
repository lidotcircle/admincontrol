package hello.admincontrol.service;

import hello.admincontrol.OperationType;

public interface APIAuthorizationService {
    /** 签发权限 Token, 具有最大有效验证次数
     * @param subject token 的主体
     * @param operationType token所指示的操作
     * @param timeoutMs token的有效时间
     * @param no 有效验证次数
     */
    String issueTo(String subject, OperationType operationType, int timeoutMs, int no);

    /** 验证 Token */
    boolean validateToken(String token, OperationType OperationType, String subject);
}

