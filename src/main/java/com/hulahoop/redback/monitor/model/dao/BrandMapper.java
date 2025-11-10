package com.hulahoop.redback.monitor.model.dao;

import com.hulahoop.redback.monitor.model.dto.BrandDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    // ✅ 브랜드 전체 조회 (T_Brand 테이블)
    List<BrandDTO> selectAllBrands();
}
