package com.hulahoop.redback.merchant.model.service;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.merchant.model.dao.MerchantMapper;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    // ✅ 페이징 + 검색
    public PageResponseDTO<MerchantDTO> getMerchants(PageRequestDTO requestDTO) {

        List<MerchantDTO> list = merchantMapper.selectMerchantsPaged(requestDTO);

        long total = merchantMapper.countMerchants(requestDTO);

        return new PageResponseDTO<>(
                list,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total);
    }

    // ✅ 기존 기능 유지
    public String findMerchantCodeByName(String merchantName) {
        return merchantMapper.findMerchantCodeByName(merchantName);
    }

    // ✅ 가맹점 생성
    public void createMerchant(MerchantDTO merchantDTO) {
        // 🛡️ 안전장치: brandCode가 없으면 기본값 설정 (DB 제약조건 위반 방지)
        if (merchantDTO.getBrandCode() == null || merchantDTO.getBrandCode().trim().isEmpty()) {
            merchantDTO.setBrandCode("NB01"); // 기본값: 노바시네마
        }
        merchantMapper.insertMerchant(merchantDTO);
    }

    // ✅ 가맹점 삭제
    public void deleteMerchant(String merchantCode) {
        merchantMapper.deleteMerchant(merchantCode);
    }

    // ✅ 가맹점 수정
    public void updateMerchant(MerchantDTO merchantDTO) {
        merchantMapper.updateMerchant(merchantDTO);
    }
}
