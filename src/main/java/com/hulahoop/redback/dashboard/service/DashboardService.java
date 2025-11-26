package com.hulahoop.redback.dashboard.service;

import com.hulahoop.redback.dashboard.model.dao.DashboardMapper;
import com.hulahoop.redback.dashboard.model.dto.DashboardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    public DashboardService(DashboardMapper dashboardMapper) {
        this.dashboardMapper = dashboardMapper;
    }

    @Transactional(readOnly = true)
    public DashboardDTO getDashboardData() {
        DashboardDTO dto = new DashboardDTO();

        dto.setTotalMembers(dashboardMapper.countTotalMembers());
        dto.setTotalMerchants(dashboardMapper.countTotalMerchants());
        dto.setTotalApiRequests(dashboardMapper.countTotalApiRequests());
        dto.setTotalTransactions(dashboardMapper.countTotalTransactions());

        dto.setDailyTransactions(dashboardMapper.selectDailyTransactions());
        dto.setMonthlyTransactions(dashboardMapper.selectMonthlyTransactions());
        dto.setCategoryRatio(dashboardMapper.selectMerchantUsage());

        return dto;
    }
}
