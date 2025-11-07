package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantMapper {
    List<MerchantDTO> selectAllMerchants();
}
