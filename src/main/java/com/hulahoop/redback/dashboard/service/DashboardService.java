package com.hulahoop.redback.dashboard.service;

// 대시보드에 표시할 통계 데이터를 조립하는 서비스 클래스
// DB에서 회원, 가맹점, 결제 통계를 각각 조회한 뒤 DashboardDTO 하나에 담아 반환한다
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

    // 대시보드에 필요한 모든 집계를 모아 DTO로 만들어 반환하는 메인 메서드
    @Transactional(readOnly = true)
    public DashboardDTO getDashboardData() {
        DashboardDTO dto = new DashboardDTO();

        // 대시보드 상단 카드에 표시되는 요약 수치 4가지를 각각 조회
        dto.setTotalMembers(dashboardMapper.countTotalMembers());
        dto.setTotalMerchants(dashboardMapper.countTotalMerchants());
        dto.setTotalApiRequests(dashboardMapper.countTotalApiRequests());
        dto.setTotalTransactions(dashboardMapper.countTotalTransactions());

        // 일별 거래 데이터를 DB에서 가져오되, 거래가 없는 날은 DB 결과에 빠져 있어서
        // 최근 7일치를 온전히 채우기 위해 없는 날짜는 amount=0으로 직접 보완한다
        List<Map<String, Object>> rawDailyData = dashboardMapper.selectDailyTransactions();
        List<Map<String, Object>> filledDailyData = new ArrayList<>();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        // DB 결과를 date 키 기준으로 빠르게 찾기 위해 Map으로 변환
        Map<String, Double> dailyDataMap = new HashMap<>();
        for (Map<String, Object> data : rawDailyData) {
            String date = (String) data.get("date");
            Number amount = (Number) data.get("amount");
            dailyDataMap.put(date, amount.doubleValue());
        }

        // 오늘 포함 최근 7일을 오래된 날짜부터 순서대로 순회
        // DB에 해당 날짜 데이터가 없으면 amount를 0.0으로 채워 넣는다
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
