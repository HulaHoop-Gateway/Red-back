package com.hulahoop.redback.merchant.controller;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import com.hulahoop.redback.merchant.model.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/merchants")
@CrossOrigin(origins = "http://localhost:5173")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    /**
     * ✅ 기본 가맹점 목록 조회
     */
    @GetMapping
    public List<MerchantDTO> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    /**
     * ✅ 가맹점별 거래 금액, 건수, 점유율 통계
     */
    @GetMapping("/usage-stats")
    public List<Map<String, Object>> getMerchantUsageStats() {
        return merchantService.getMerchantUsageStats();
    }
}
