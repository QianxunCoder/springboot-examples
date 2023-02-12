package com.github.codeqingkong.springsecurity.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: QingKong
 * @date: 2023/1/3
 */
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 认证成功
//        ObjectMapper objectMapper = new ObjectMapper();
//        String resultJson = objectMapper.writeValueAsString(authentication);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().println(resultJson);
        request.getRequestDispatcher("/index").forward(request,response);
    }
}
