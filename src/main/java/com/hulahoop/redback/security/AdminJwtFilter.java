package com.hulahoop.redback.security;

// AdminJwtFilter
// Spring Security 필터 체인에 등록된 HTTP 요청별 JWT 인증 필터
// /api/admin/** 경로에 대해서만 동작하며, 로그인(/login)은 검원함
// OncePerRequestFilter 상속으로 요청 당 정확히 1번만 실행됨
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminJwtFilter extends OncePerRequestFilter {

    private final AdminJwtUtil adminJwtUtil;

    public AdminJwtFilter(AdminJwtUtil adminJwtUtil) {
        this.adminJwtUtil = adminJwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        System.out.println("[AdminJwtFilter] 요청: " + method + " " + path);

        // 관리자 로그인은 JWT 없이도 도달 가능한 공개 경로이므로 필터를 바로 통과시킸
        if (isAdminPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // /api/admin/** 경로 이외의 요청 (Merchant, Transaction 등 다른 API)은 JWT 검사 교낙 없이 통과
        if (!path.startsWith("/api/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더 존재 여부 및 Bearer 포맷 확인
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Missing Authorization Header");
            return;
        }

        // "Bearer " 접두어(7자리) 제거 후 실제 토큰 문자열 추쳙
        String token = authHeader.substring(7);

        // 토큰 유효성 검사: 서명 매칭 및 만료 여부 확인
        if (!adminJwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid or Expired Token");
            return;
        }

        // 토큰에서 관리자 ID를 추쳙하여 Spring Security 컨텍스트에 인증 정보 등록
        String adminId = adminJwtUtil.extractAdminId(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminId, null,
                null);

        // 인증 정보를 컨텍스트에 저장해 이후 스프링 보안 외계에서 인증원으로 승인됨
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    // 컨트롤러가 도달하기 전에 JWT 필터를 스킵해야 할 공개 경로 판단 메서드
    private boolean isAdminPublicPath(String path) {
        return path.startsWith("/api/admin/login");
    }
}
