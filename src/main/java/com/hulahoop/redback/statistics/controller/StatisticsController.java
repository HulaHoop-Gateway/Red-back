package com.hulahoop.redback.statistics.controller;

// StatisticsController
// 마이카테고리별 거래 데이터, 요효취소 비율 등 통계 집계 데이터를 페이징으로 제공하는 API
import org.springframework.web.bind.annotation.*;
import com.hulahoop.redback.statistics.model.dto.StatisticsDTO;
import com.hulahoop.redback.statistics.model.service.StatisticsService;
import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    // 생성자 주입
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // GET /api/statistics?page=&size=&keyword= ...
    // 쿠리 파라미터(startDate, endDate, merchantCode, categoryCode, brandCode 등)가
    // PageRequestDTO로 년어오면 해당 조건으로 필터링해서 통계 목록 리턴
    @GetMapping
    public PageResponseDTO<StatisticsDTO> getStatistics(PageRequestDTO pageRequestDTO) {
        return statisticsService.getStatistics(pageRequestDTO);
    }
}
