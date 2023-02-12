package com.github.codeqingkong.springsecurity.authentication.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**自定义注销登录处理
 * @author QingKong
 * @date 2023/2/12
 */
public class SecurityLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 注销登录
        // 注销登录后跳转到登录页
        response.sendRedirect("/form/login");
    }
}
