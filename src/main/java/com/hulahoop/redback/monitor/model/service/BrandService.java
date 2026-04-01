package com.hulahoop.redback.monitor.model.service;

// BrandService
// 서버 모니터링 화면을 위한 브랜드 정보 + 헬스체크 URL 병합 서비스
// DB에서 카다로그 정보(브랜드코드, 이름 등)를 가져오고,
// application.yml에 설정된 서버 URL/포트 정보를 브랜드코드 키로 매칭해 동적 취합
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.config.ServerConfig;
import com.hulahoop.redback.monitor.model.dao.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandMapper brandMapper; // DB 매퍼: T_Brand 테이블 조회
    private final ServerConfig serverConfig; // yml 설정: 서버 URL/포트/헬스패스 목록

    // 생성자 주입
    public BrandService(BrandMapper brandMapper, ServerConfig serverConfig) {
        this.brandMapper = brandMapper;
        this.serverConfig = serverConfig;
    }

    // 페이징 블록 + 키워드 필터 수용 서버 목록 조회
    // DB에서 가져온 각 브랜드 로우에 yml 설정에서 읽어온 URL 정보를 브랜드코드 기준으로 JoinStream
    public PageResponseDTO<Map<String, Object>> getMergedServersPaged(PageRequestDTO requestDTO) {

        // 1단계: DB에서 페이징 서버(브랜드) 목록 조회
        List<Map<String, Object>> brands = brandMapper.selectServersPaged(requestDTO);

        long total = brandMapper.countServers(requestDTO);

        // 2단계: 각 브랜드로우에 yml 에서 데이와 해당 URL/포트/헬스패스를 찾아 브랜드코드로 매칭
        // yml에 등록되지 않은 브랜드이면 모두 null로 리턴
        List<Map<String, Object>> merged = brands.stream().map(b -> {
            Map<String, Object> map = new HashMap<>(b);

            serverConfig.getServers().stream()
                    .filter(s -> s.getBrandCode().equals(b.get("brandCode")))
                    .findFirst()
                    .ifPresentOrElse(
                            s -> {
                                map.put("baseUrl", s.getBaseUrl());
                                map.put("port", s.getPort());
                                map.put("healthPath", s.getHealthPath());
                            },
                            () -> {
                                map.put("baseUrl", null);
                                map.put("port", null);
                                map.put("healthPath", null);
                            });

            return map;
        }).collect(Collectors.toList());

        return new PageResponseDTO<>(
                merged,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total);
    }
}
