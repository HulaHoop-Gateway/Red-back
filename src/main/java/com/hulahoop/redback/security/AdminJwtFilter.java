package com.hulahoop.redback.security;

// Spring Security 필터 체인에 등록되는 JWT 인증 필터
// /api/admin/** 경로에 대해서만 동작하고, 로그인 경로는 건너뛴다
// OncePerRequestFilter를 상속받았기 때문에 요청당 정확히 한 번만 실행된다
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

        // 로그인 자체는 토큰 없이 접근 가능한 공개 경로이므로 바로 통과시킨다
        if (isAdminPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // /api/admin/** 이외의 경로(가맹점, 결제 등)는 이 필터를 거치지 않고 넘어간다
        if (!path.startsWith("/api/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더가 없거나 Bearer 형식이 아니면 403 반환
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Missing Authorization Header");
            return;
        }

        // "Bearer " 앞 7글자를 제거하고 실제 토큰 문자열만 꺼낸다
        String token = authHeader.substring(7);

        // 서명 검증 및 만료 여부 확인
        if (!adminJwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid or Expired Token");
            return;
        }

        // 토큰에서 관리자 ID를 꺼내 Spring Security 인증 컨텍스트에 등록
        String adminId = adminJwtUtil.extractAdminId(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminId, null,
                null);

        // 인증 정보를 컨텍스트에 저장해두면 이후 Security 체인에서 인증된 사용자로 처리된다
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    // JWT 검증을 건너뛰어야 하는 공개 경로인지 판단하는 메서드
    private boolean isAdminPublicPath(String path) {
        return path.startsWith("/api/admin/login");
    }
}
