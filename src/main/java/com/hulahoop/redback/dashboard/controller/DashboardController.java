package com.hulahoop.redback.dashboard.controller;

// DashboardController
// 어드민 대시보드 메인 화면에 필요한 요약 데이터를 먹여주는 API 엔드포인트
// 회원 수, 가맹점 수, 결제 요약, 일/월별 그래프 데이터 등을 단건 API로 제공
import com.hulahoop.redback.dashboard.model.dto.DashboardDTO;
import com.hulahoop.redback.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    // 생성자 주입
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // GET /api/v1/dashboard
    // 대시보드에 필요한 모든 통계 데이터를 단건 DTO로 취합해 리턴
    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboardData() {
        System.out.println("Dashboard API called");
        return ResponseEntity.ok(dashboardService.getDashboardData());
    }
}
