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

// Red-back(어드민 서버) Spring Security 설정 클래스
@Configuration
public class SecurityConfig {

    private final AdminJwtFilter adminJwtFilter;

    // JWT 커스텀 필터를 생성자 주입으로 받아온다
    public SecurityConfig(AdminJwtFilter adminJwtFilter) {
        this.adminJwtFilter = adminJwtFilter;
    }

    // 시큐리티 필터 체인 전체 설정을 한 곳에 모아 빈으로 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsSource()))
                .csrf(csrf -> csrf.disable())                                          // REST API이므로 CSRF 비활성화
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT 기반이므로 세션 미사용

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()        // CORS 프리플라이트 요청 허용

                        // 관리자 로그인은 토큰 없이 접근 허용
                        .requestMatchers("/api/admin/login").permitAll()

                        // 그 외 /api/admin/** 경로는 인증 필요
                        .requestMatchers("/api/admin/**").authenticated()

                        // 나머지 API는 별도 인증 없이 허용
                        .anyRequest().permitAll())

                .formLogin(f -> f.disable())

                // 관리자 JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 끼워 넣는다
                .addFilterBefore(adminJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 비밀번호 암호화에 사용하는 BCrypt 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager를 직접 꺼내 쓸 일이 있을 때를 위한 빈 등록
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // CORS 전역 정책 설정
    // 프론트(React)에서 인증 헤더를 붙인 요청을 보낼 수 있도록 Credentials 허용 및 Authorization 헤더 노출
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
