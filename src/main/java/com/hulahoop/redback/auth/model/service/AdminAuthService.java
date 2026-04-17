package com.hulahoop.redback.auth.model.service;

// 관리자 로그인 비즈니스 로직을 담당하는 서비스 클래스
// DB에서 관리자 계정을 조회하고, 비밀번호 검증 후 JWT 토큰을 발급한다
import com.hulahoop.redback.auth.model.dao.AuthMapper;
import com.hulahoop.redback.auth.model.dto.AuthDTO;
import com.hulahoop.redback.security.AdminJwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminAuthService {

    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder encoder;  // BCrypt 해시 비교에 사용
    private final AdminJwtUtil adminJwtUtil;       // JWT 생성 처리

    // 생성자 주입으로 세 가지 의존성을 받아온다
    public AdminAuthService(AuthMapper authMapper,
            BCryptPasswordEncoder encoder,
            AdminJwtUtil adminJwtUtil) {
        this.authMapper = authMapper;
        this.encoder = encoder;
        this.adminJwtUtil = adminJwtUtil;
    }

    // 관리자 로그인 전체 흐름을 처리하는 메서드
    public Map<String, String> login(String id, String password) {

        // 1단계: DB에서 해당 id의 관리자 계정 조회 (user_type='A' 조건 포함)
        AuthDTO admin = authMapper.findAdminById(id);

        // 계정이 없으면 구체적인 원인은 숨기고 동일한 메시지로 예외를 던진다
        if (admin == null) {
            throw new RuntimeException("관리자 계정 또는 비밀번호 오류");
        }

        // 2단계: 입력한 비밀번호와 DB에 저장된 BCrypt 해시값을 비교
        if (!encoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("관리자 계정 또는 비밀번호 오류");
        }

        // 3단계: 인증 성공이면 JWT 토큰을 만들고 토큰 문자열과 관리자 이름을 Map으로 리턴
        String token = adminJwtUtil.generateToken(admin.getId());

        return Map.of(
                "token", token,
                "adminName", admin.getName());
    }
}
