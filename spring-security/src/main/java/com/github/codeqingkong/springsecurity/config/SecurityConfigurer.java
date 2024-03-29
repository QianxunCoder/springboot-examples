package com.github.codeqingkong.springsecurity.config;

import com.github.codeqingkong.springsecurity.handler.SecurityAuthenticationFailureHandler;
import com.github.codeqingkong.springsecurity.handler.SecurityAuthenticationSuccessHandler;
import com.github.codeqingkong.springsecurity.handler.SecurityLogoutHandler;
import com.github.codeqingkong.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigurer {
    private final UserServiceImpl userDetailsService;
    private final CustomSecurityMetadataSource customSecurityMetadataSource;

    /**
     * 配置过滤器
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */



    /**
     * 配置 WebSecurity，SpringSecurity 5.7 推荐用 WebSecurityCustomizer 替代 WebSecurity
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/ignore1","/ignore2");
    }

    /**
     * 认证成功后处理
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new SecurityAuthenticationSuccessHandler();
    }

    /**
     * 认证失败后处理
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new SecurityAuthenticationFailureHandler();
    }

    /**
     * 注销登录成功后处理
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new SecurityLogoutHandler();
    }

    /**
     * 返回全局的 AuthenticationManager
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 设置认证数据源
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }
}
