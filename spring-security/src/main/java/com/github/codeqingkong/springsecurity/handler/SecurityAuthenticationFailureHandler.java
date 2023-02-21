package com.github.codeqingkong.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: QingKong
 * @date: 2023/1/4
 */
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 认证失败
        ObjectMapper objectMapper = new ObjectMapper();
        String failureJson = objectMapper.writeValueAsString(exception);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(failureJson);
    }
}
