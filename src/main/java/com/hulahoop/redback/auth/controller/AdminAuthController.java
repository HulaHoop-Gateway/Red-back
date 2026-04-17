package com.hulahoop.redback.auth.controller;

// 관리자 로그인 요청을 처리하는 컨트롤러
// 요청이 들어오면 서비스 계층으로 넘기고, JWT 토큰과 관리자 이름을 응답으로 돌려준다
import com.hulahoop.redback.auth.model.service.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    // 생성자 주입 방식으로 서비스와 연결
    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    // POST /api/admin/login
    // 요청 바디에서 id, password를 꺼내 로그인 처리 후 JWT 토큰과 관리자 이름을 반환
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> req) {

        Map<String, String> response = adminAuthService.login(
                req.get("id"),
                req.get("password"));

        return ResponseEntity.ok(response);
    }
}