package com.hulahoop.redback.auth.controller;

// Red-back 어드민 인증 컨트롤러
// 관리자 계정 로그인 요청을 받아 JWT 토큰을 발급해 주는 진입점
import com.hulahoop.redback.auth.model.service.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    // 생성자 주입 방식으로 서비스 계층과 연결
    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    // POST /api/admin/login
    // 요청 바디에서 id, password 를 받아 로그인 처리 후 JWT + 관리자 이름 반환
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> req) {

        Map<String, String> response = adminAuthService.login(
                req.get("id"),
                req.get("password"));

        return ResponseEntity.ok(response);
    }
}