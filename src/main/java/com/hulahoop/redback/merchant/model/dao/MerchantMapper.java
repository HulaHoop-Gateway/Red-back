package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

    List<MerchantDTO> selectAllMerchants();
}
