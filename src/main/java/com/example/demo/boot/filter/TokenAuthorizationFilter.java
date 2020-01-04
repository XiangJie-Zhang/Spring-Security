package com.example.demo.boot.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.boot.auth.SelfUserDetails;
import com.example.demo.boot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证token的filter，这个filter就从请求头中拿到token字符串，
 * 然后用TokenUtils检验token的合法性，
 * 如果合法就解析出相应的信息，然后组装成Authentication，
 * 最后放到SecurityContext中
 */
@Component
@PropertySource("classpath:system.properties")
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private String HEADER_NAME;

    private final TokenUtils tokenUtils;

    public TokenAuthorizationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(httpServletRequest);

        if (tokenUtils.validateToken(token)) {
            // 从用户传来的用户信息，这里不信任
            SelfUserDetails userTo = (SelfUserDetails) tokenUtils.getAuthentication(token);
            if (userTo != null) {
                // 这个请求是已经通过验证的，把权限放进去
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userTo, null,
                                userTo.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else if (token != null) {
            ResponseEntity<String> rs = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "token has expired");

            httpServletResponse.getWriter().write(JSON.toJSONString(rs));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        cleanAuthentication();
    }

    /**
     * 从请求头解析出token
     *
     * @param request 请求头
     * @return token 用户传送的token
     */
    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_NAME);
        if (token == null || !token.startsWith("Bearer "))
            return null;
        else
            return token.substring(7);
    }

    private void cleanAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Value("${head_name}")
    public void setHEADER_NAME(String HEADER_NAME) {
        this.HEADER_NAME = HEADER_NAME;
    }

}
