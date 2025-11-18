package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

    // 🔹 전체 가맹점 조회
    List<MerchantDTO> selectAllMerchants();

    // ⭐ branch_name(=merchant_name) → merchant_code 조회
    String findMerchantCodeByName(String merchantName);
}
