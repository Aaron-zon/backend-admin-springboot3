package com.monousoooo.backendadminspringboot3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests ->
                // authorizeHttpRequests: 针对http请求进行授权配置
                authorizeHttpRequests
                        // login 登录页面需要匿名访问；permitAll: 具有所有权限 也就是可以匿名访问
                        .requestMatchers("/login").permitAll()
                        // anyRequest: 任何请求 所有请求; authenticated: 认证登录
                        .anyRequest().authenticated()
        );

        http.formLogin(formLogin -> formLogin
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
        );

        // 关闭csrf
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
