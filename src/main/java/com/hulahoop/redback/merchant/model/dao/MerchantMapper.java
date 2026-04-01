package com.hulahoop.redback.merchant.model.dao;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

    // 페이징 목록 조회 매핑
    List<MerchantDTO> selectMerchantsPaged(PageRequestDTO requestDTO);

    // 전체 데이터 건수 조회 매핑
    long countMerchants(PageRequestDTO requestDTO);

    // 기존 가맹점 단건 이름 검색 매핑 유지
    String findMerchantCodeByName(String merchantName);

    // 신규 가맹점 인서트 매핑
    void insertMerchant(MerchantDTO merchantDTO);

    // 가맹점 역방향 물리 삭제 매핑
    void deleteMerchant(String merchantCode);

    // 기존 가맹점 정보 업데이트 매핑
    void updateMerchant(MerchantDTO merchantDTO);
}
