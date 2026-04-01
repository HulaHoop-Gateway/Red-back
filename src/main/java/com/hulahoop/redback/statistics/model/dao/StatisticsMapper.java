package com.hulahoop.redback.statistics.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;

// 통계 구리 전용 MyBatis 매퍼
@Mapper
public interface StatisticsMapper {

    // 여러 필터 조건(날짜범위, 가맹점코드, 카테고리, 브랜드)을 지원하는 통계 목록 조회
    List<StatisticsDTO> selectStatistics(PageRequestDTO pageRequestDTO);

    // 페이징네이션을 위한 전체 통계 븀록 수 카운트 (동일 필터 조건 적용)
    long countStatistics(PageRequestDTO pageRequestDTO);
}
