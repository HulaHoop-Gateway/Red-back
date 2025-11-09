package com.hulahoop.redback.merchant.model.service;

import com.hulahoop.redback.merchant.model.dao.MerchantMapper;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * ✅ 단순 가맹점 목록 조회 (DTO 반환)
     */
    public List<MerchantDTO> getAllMerchants() {
        return merchantMapper.selectAllMerchants();
    }

    /**
     * ✅ 가맹점별 거래금액 / 점유율 계산 (통계용)
     */
    public List<Map<String, Object>> getMerchantUsageStats() {
        List<Map<String, Object>> list = merchantMapper.selectMerchantUsageStats();
        BigDecimal total = merchantMapper.selectTotalAmount();

        if (total == null) total = BigDecimal.ZERO;

        for (Map<String, Object> m : list) {
            BigDecimal amount = (BigDecimal) m.get("total_amount");
            if (amount == null) amount = BigDecimal.ZERO;

            BigDecimal percent = total.compareTo(BigDecimal.ZERO) > 0
                    ? amount.divide(total, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                    : BigDecimal.ZERO;

            m.put("percent", percent);
        }

        return list;
    }
}
