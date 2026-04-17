package com.hulahoop.redback.security;

// JWT 생성, 검증, 페이로드 추출을 담당하는 유틸 클래스
// secret과 만료 시간은 application.yml에서 주입받는다
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class AdminJwtUtil {

    @Value("${admin.jwt.secret}")
    private String secret;

    @Value("${admin.jwt.expiration-ms}")
    private long expirationMs;

    private Key secretKey;

    // 빈 초기화 시점에 문자열 secret을 HMAC-SHA 키 객체로 변환해 저장
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 관리자 ID를 subject로 넣어 JWT를 생성하고 문자열로 반환
    public String generateToken(String adminId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    // 서명 검증과 만료 여부를 동시에 확인하고, 문제가 있으면 false를 돌려준다
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 토큰의 subject 클레임에서 관리자 ID를 꺼내 반환
    public String extractAdminId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
