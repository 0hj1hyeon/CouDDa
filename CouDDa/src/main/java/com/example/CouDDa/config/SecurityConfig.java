package com.example.CouDDa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 기존 회원가입/로그인 경로에 추가 API 경로들을 포함합니다.
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login",
                                "/api/sellers",       // 판매자 등록/조회 API 허용
                                "/api/categories",    // 카테고리 등록/조회 API 허용
                                "/api/products",       // 상품 등록/조회 API 허용
                                "/api/orders/*"
                        ).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}