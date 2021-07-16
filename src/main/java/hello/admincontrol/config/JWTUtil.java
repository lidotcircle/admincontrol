package hello.admincontrol.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * JSON Web Token 生成, 验证, 获取字段操作
 */
@Component
public class JWTUtil {
    @Value("#{new Long('${jwt.aliveTimeFrame_second}')}")
    public Long jwtAliveTimeFrame_s;

    @Value("${jwt.secret}")
    private String secret;

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(String subject,Map<String, Object> claims, long liveMs) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + liveMs))
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return this.generateToken(subject, claims, jwtAliveTimeFrame_s * 1000);
    }

    public Boolean validateToken(String token, String username) {
        final String jwtUsername = this.getUsernameFromToken(token);
        return (jwtUsername.equals(username) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Object getObjectFromToken(String token, String field) {
        return this.getClaimFromToken(token, claims -> claims.get(field));
    }
}

