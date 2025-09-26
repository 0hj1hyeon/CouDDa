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
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                // URL별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // 회원 가입, 로그인 모두 접근 허용
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        // 그 외 모든 요청은 로그인해야댐
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}