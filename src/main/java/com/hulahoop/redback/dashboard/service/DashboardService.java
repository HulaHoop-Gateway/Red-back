package com.hulahoop.redback.dashboard.service;

// DashboardService
// 대시보드 요약 집계 데이터를 구성하는 서비스
// DB에서 회원/가맹점/결제 통계를 각각 조회한 후 DashboardDTO 하나에 모아 리턴
import com.hulahoop.redback.dashboard.model.dao.DashboardMapper;
import com.hulahoop.redback.dashboard.model.dto.DashboardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    // 생성자 주입
    public DashboardService(DashboardMapper dashboardMapper) {
        this.dashboardMapper = dashboardMapper;
    }

    // 대시보드에서 필요한 모든 집계 데이터를 하나의 DTO로 조립하는 메인 메서드
    @Transactional(readOnly = true)
    public DashboardDTO getDashboardData() {
        DashboardDTO dto = new DashboardDTO();

        // 대시보드 상단 카드용 요약 숫자 캔보레이션 조회
        dto.setTotalMembers(dashboardMapper.countTotalMembers());
        dto.setTotalMerchants(dashboardMapper.countTotalMerchants());
        dto.setTotalApiRequests(dashboardMapper.countTotalApiRequests());
        dto.setTotalTransactions(dashboardMapper.countTotalTransactions());

        // 일별 거래 데이터: DB에 하루치 없는 날은 DB 결과에 놀락나므로 마지막 7일치 완전한 데이터를 보장하기 위해 빈 날짜를 0으로 쳄우는
        // 로직
        List<Map<String, Object>> rawDailyData = dashboardMapper.selectDailyTransactions();
        List<Map<String, Object>> filledDailyData = new ArrayList<>();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        // DB 조회 결과를 date 키 기준 Map으로 빠르게 검색할 수 있도록 폴딩
        Map<String, Double> dailyDataMap = new HashMap<>();
        for (Map<String, Object> data : rawDailyData) {
            String date = (String) data.get("date");
            Number amount = (Number) data.get("amount");
            dailyDataMap.put(date, amount.doubleValue());
        }

        // 오늘 포함 최근 7일치 (오늘 ~ 6일 전) 순서대로 순회
        // DB에 해당 날짜 데이터가 없으면 amount 0.0으로 수동 보완
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dateStr = date.format(formatter);

            Map<String, Object> dayData = new HashMap<>();
            dayData.put("date", dateStr);
            dayData.put("amount", dailyDataMap.getOrDefault(dateStr, 0.0));
            filledDailyData.add(dayData);
        }

        dto.setDailyTransactions(filledDailyData);
        dto.setMonthlyTransactions(dashboardMapper.selectMonthlyTransactions());
        dto.setCategoryRatio(dashboardMapper.selectCategoryRatio());

        return dto;
    }
}
