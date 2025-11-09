package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface MerchantMapper {
    List<MerchantDTO> selectAllMerchants();
    List<Map<String, Object>> selectMerchantUsageStats();
    java.math.BigDecimal selectTotalAmount();
}
