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
// // Spring 6.x 이상 버전에 맞춘 allowedOriginPatterns 적용 (기존 * 대체)
// .allowedOriginPatterns("http://localhost:3000")
// .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// .allowCredentials(true);
// }
// };
// }
// }
