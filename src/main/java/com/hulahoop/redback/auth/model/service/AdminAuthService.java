package com.hulahoop.redback.auth.model.service;

// Red-back 어드민 인증 서비스
// 관리자 로그인 비즈니스 로직 담당
// DB에서 관리자 계정 조회 후 BCrypt 암호 검증, 성공하면 JWT 토큰 발급
import com.hulahoop.redback.auth.model.dao.AuthMapper;
import com.hulahoop.redback.auth.model.dto.AuthDTO;
import com.hulahoop.redback.security.AdminJwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminAuthService {

    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder encoder; // 비밀번호 암호화/검증용 인코더
    private final AdminJwtUtil adminJwtUtil; // JWT 생성 및 검증 유틸리티

    // 생성자 주입 방식으로 세 개 의존성 보장
    public AdminAuthService(AuthMapper authMapper,
            BCryptPasswordEncoder encoder,
            AdminJwtUtil adminJwtUtil) {
        this.authMapper = authMapper;
        this.encoder = encoder;
        this.adminJwtUtil = adminJwtUtil;
    }

    // 관리자 로그인 전체 플로우 메인 메서드
    public Map<String, String> login(String id, String password) {

        // 1단계: DB에서 관리자(user_type='A') 계정을 id 기준으로 조회
        AuthDTO admin = authMapper.findAdminById(id);

        // 해당 id로 등록된 관리자가 없으면 즉시 예외 토스 (실제 원인은 노칬)
        if (admin == null) {
            throw new RuntimeException("관리자 계정 또는 비밀번호 오류");
        }

        // 2단계: BCrypt 해시 매칭으로 입력 암호와 DB 저장본 비교 검증
        if (!encoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("관리자 계정 또는 비밀번호 오류");
        }

        // 3단계: 인증 성공 시 JWT 토큰 생성 후 토큰 문자열 + 관리자 이름을 Map으로 리턴
        String token = adminJwtUtil.generateToken(admin.getId());

        return Map.of(
                "token", token,
                "adminName", admin.getName());
    }
}
