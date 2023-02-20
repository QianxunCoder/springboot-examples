package com.github.codeqingkong.springsecurity.config;

import com.github.codeqingkong.springsecurity.authentication.handler.SecurityAuthenticationFailureHandler;
import com.github.codeqingkong.springsecurity.authentication.handler.SecurityAuthenticationSuccessHandler;
import com.github.codeqingkong.springsecurity.authentication.handler.SecurityLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author: QingKong
 * @date: 2023/1/2
 */
@Configuration
@EnableWebSecurity
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
                .formLogin(login -> login
                        .loginPage("/form/login")
                        .loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                )
                .cors().disable()
                .userDetailsService(userDetailsService());
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
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("admin")
                .password("{noop}123456")
                .roles("ADMIN")
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return inMemoryUserDetailsManager;
    }
}
