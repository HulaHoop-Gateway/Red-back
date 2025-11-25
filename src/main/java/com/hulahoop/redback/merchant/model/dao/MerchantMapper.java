package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

    // ✅ 페이징 조회
    List<MerchantDTO> selectMerchantsPaged(PageRequestDTO requestDTO);

    // ✅ 총 개수 조회
    long countMerchants(PageRequestDTO requestDTO);

    // ✅ 기존 유지
    String findMerchantCodeByName(String merchantName);
}
