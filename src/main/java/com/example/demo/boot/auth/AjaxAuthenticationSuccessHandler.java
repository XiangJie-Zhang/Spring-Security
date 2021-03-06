package com.example.demo.boot.auth;

import com.alibaba.fastjson.JSON;
import com.example.demo.boot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private TokenUtils tokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");

        responseBody.setJwtToken(tokenUtils.generateToken(SecurityContextHolder.getContext().getAuthentication()));

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

    @Autowired
    public AjaxAuthenticationSuccessHandler(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }
}

