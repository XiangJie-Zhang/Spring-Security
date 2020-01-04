package com.example.demo.boot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.boot.config.SelfUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenUtils {

    //Secret密钥
    private static final String SECRET = "auth_chm";

    //token有效期（分钟）
    private static final long VALIDATE_MINUTE = 60 * 24;

    //加密算法
    private final Algorithm algorithm;

    public TokenUtils() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(SECRET);
    }

    /**
     * 根据用户信息生成token
     *
     * @param authentication 用户认证信息
     * @return jwt内容
     */
    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = Date.from(Instant.now());
        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(VALIDATE_MINUTE).toInstant());

        //create jwt
        return JWT.create()
                .withClaim("authorities", authorities)
                .withSubject(authentication.getName())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    /**
     * 认证token有效性
     *
     * @param token jwt内容
     * @return 有效/失效
     */
    public boolean validateToken(String token) {
        if (token == null)
            return false;
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 从token中解析中用户信息
     *
     * @param token jwt信息
     * @return SelfUserDetails 用户认证信息
     */
    public SelfUserDetails getAuthentication(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        Authentication authorities = (Authentication) decodedJWT.getClaim("authorities");
        SelfUserDetails principal = (SelfUserDetails) authorities.getPrincipal();
        assert principal != null;

        return principal;
    }

}
