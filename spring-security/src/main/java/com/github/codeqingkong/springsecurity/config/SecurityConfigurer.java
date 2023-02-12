package com.github.codeqingkong.springsecurity.config;

import com.github.codeqingkong.springsecurity.authentication.handler.SecurityAuthenticationFailureHandler;
import com.github.codeqingkong.springsecurity.authentication.handler.SecurityAuthenticationSuccessHandler;
import com.github.codeqingkong.springsecurity.authentication.handler.SecurityLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Configuration
public class SecurityConfigurer {

    /**
     * 配置过滤器
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/form/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/form/login").loginProcessingUrl("/doLogin")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .cors().disable();
        return http.build();
    }


    /**
     * 配置 WebSecurity，SpringSecurity 5.7 推荐用 WebSecurityCustomizer 替代 WebSecurity
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/ignore1","/ignore2");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new SecurityAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new SecurityAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new SecurityLogoutHandler();
    }

}
