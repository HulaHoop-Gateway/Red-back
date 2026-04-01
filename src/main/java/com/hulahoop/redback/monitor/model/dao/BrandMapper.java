package com.hulahoop.redback.monitor.model.dao;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

// 서버 모니터링용 T_Brand 조회 전용 MyBatis 매퍼
@Mapper
public interface BrandMapper {

    // 페이징 + 키워드 필터 지원 브랜드 목록 조회
    // 결과는 서비스에서 yml URL 정보와 병합될 원본 역할
    List<Map<String, Object>> selectServersPaged(PageRequestDTO requestDTO);

    // 페이징네이션을 위한 전체 브랜드 수 콴트 (검색 조건 동일 적용)
    long countServers(PageRequestDTO requestDTO);
}
