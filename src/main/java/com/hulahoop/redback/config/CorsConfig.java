// 이 파일은 현재 사용하지 않아 전체 주석 처리된 상태로 보관 중
// SecurityConfig에서 CORS를 직접 설정하고 있어 별도 CorsConfig는 불필요

// package com.hulahoop.redback.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// @Configuration
// public class CorsConfig {
//
// @Bean
// public WebMvcConfigurer corsConfigurer() {
// return new WebMvcConfigurer() {
// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry.addMapping("/api/**")
// // Spring 6.x 이상에서 allowedOrigins("*")는 Credentials와 함께 쓸 수 없어 패턴 방식으로 변경
// .allowedOriginPatterns("http://localhost:3000")
// .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// .allowCredentials(true);
// }
// };
// }
// }
