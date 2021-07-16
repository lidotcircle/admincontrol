package hello.admincontrol.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.admincontrol.OperationType;
import hello.admincontrol.config.JWTUtil;
import hello.admincontrol.service.APIAuthorizationService;


@Service
public class APIAuthorizationServiceImpl implements APIAuthorizationService {
    @Autowired
    private JWTUtil jwt;


	@Override
	public String issueTo(String subject, OperationType operationType, int timeoutMs) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("operationType", operationType);
        return this.jwt.generateToken(subject, claims, timeoutMs);
	}

	@Override
	public boolean validateToken(String token, OperationType operationType, String subject) {
        if (this.jwt.validateToken(token, subject)) {
            return false;
        }
        final OperationType ope = (OperationType)this.jwt.getObjectFromToken(token, "operationType");
        return operationType == ope;
	}
}

