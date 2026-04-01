package com.hulahoop.redback.config;

import com.hulahoop.redback.security.AdminJwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.List;

// Red-back(어드민 서버)용 Spring Security 환경 설정 클래스
@Configuration
public class SecurityConfig {

    private final AdminJwtFilter adminJwtFilter;

    // JWT 커스텀 필터 의존성 주입
    public SecurityConfig(AdminJwtFilter adminJwtFilter) {
        this.adminJwtFilter = adminJwtFilter;
    }

    // 시큐리티 설정 체인 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 관리자 로그인 공개
                        .requestMatchers("/api/admin/login").permitAll()

                        // 관리자 API 보호
                        .requestMatchers("/api/admin/**").authenticated()

                        // 나머지는 그냥 허용
                        .anyRequest().permitAll())

                .formLogin(f -> f.disable())

                // ⭐ 관리자 JWT 필터만 등록
                .addFilterBefore(adminJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 관리자 비밀번호 암호화 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 사용을 위한 빈 등록
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // CORS(Cross-Origin Resource Sharing) 글로벌 정책 설정
    // 리액트 등 SPA 연동을 위해 로컬 호스트, Credentials 허용 및 Authorization 헤더 노출
    @Bean
    public CorsConfigurationSource corsSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
