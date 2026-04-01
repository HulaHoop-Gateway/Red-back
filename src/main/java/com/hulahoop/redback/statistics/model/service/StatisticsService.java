package com.hulahoop.redback.statistics.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.statistics.model.dao.StatisticsMapper;
import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;

// StatisticsService - 가맹점별 거래 통계를 취합하는 서비스 담당
@Service
public class StatisticsService {

    // 통계 쿼리를 담당하는 MyBatis 매퍼
    private final StatisticsMapper statisticsMapper;

    // 생성자 주입
    public StatisticsService(StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }

    // 필터 조건(날짜범위, 가맹점, 카테고리 등)을 지원하는 통계 단라를 페이징으로 취합해 리턴
    public PageResponseDTO<StatisticsDTO> getStatistics(PageRequestDTO req) {

        List<StatisticsDTO> list = statisticsMapper.selectStatistics(req); // 필터 적용 통계 목록
        long total = statisticsMapper.countStatistics(req); // 페이징용 전체 건수

        return new PageResponseDTO<>(list, req.getPage(), req.getSize(), total);
    }
}
