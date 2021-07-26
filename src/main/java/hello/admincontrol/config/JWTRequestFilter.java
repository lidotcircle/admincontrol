package hello.admincontrol.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hello.admincontrol.exception.BadRequest;
import io.jsonwebtoken.ExpiredJwtException;


/**
 * 利用 JSON Web Token 验证用户身份
 * !!! 只验证URI以`/apis/`开头的请求
 */
@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JWTRequestFilter.class);
    private static final ArrayList<String> bypassURIs;
    static {
        bypassURIs = new ArrayList<String>();
        bypassURIs.add("/apis/ping");
        bypassURIs.add("/apis/user/register");
        bypassURIs.add("/apis/auth/refresh-token");
        bypassURIs.add("/apis/auth/jwt");
        bypassURIs.add("/apis/auth-token");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        log.info("filtering HTTP Request for: " + request.getMethod() + " " + request.getRequestURI());

        boolean bypass = !request.getRequestURI().startsWith("/apis/");
        for(String uri: bypassURIs) {
            if(request.getRequestURI().startsWith(uri)) {
                bypass = true;
                break;
            }
        }

        if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
            bypass = true;
        }

        if(!bypass) {
            final String jwtStr = request.getHeader("X-Auth");

            String username = null;
            if (jwtStr != null) {
                try {
                    username = jwtUtil.getUsernameFromToken(jwtStr);

                    if(username == null) {
                        throw new BadRequest("错误的 JSON Web Token");
                    } else {
                        request.setAttribute("username", username);
                    }
                } catch (IllegalArgumentException e) {
                    throw new BadRequest("错误的 JSON Web Token");
                } catch (ExpiredJwtException e) {
                    throw new BadRequest("JSON Web Token 已过期");
                }
            } else {
                throw new BadRequest("需要 JSON Web Token 进行身份认证");
            }
        }

        chain.doFilter(request, response);
    }

}

