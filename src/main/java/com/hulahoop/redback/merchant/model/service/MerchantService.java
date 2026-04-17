package com.hulahoop.redback.merchant.model.service;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.merchant.model.dao.MerchantMapper;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    // 페이징 및 검색 기능 통합 제공
    public PageResponseDTO<MerchantDTO> getMerchants(PageRequestDTO requestDTO) {

        List<MerchantDTO> list = merchantMapper.selectMerchantsPaged(requestDTO);

        long total = merchantMapper.countMerchants(requestDTO);

        return new PageResponseDTO<>(
                list,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total);
    }

    // 기존 가맹점 코드 단건 조회 기능 유지
    public String findMerchantCodeByName(String merchantName) {
        return merchantMapper.findMerchantCodeByName(merchantName);
    }

    // 가맹점 데이터 인서트 처리
    public void createMerchant(MerchantDTO merchantDTO) {
        // DB 제약조건 위반 방지를 위해 브렌드코드 누락 시 기본값 세팅 보장
        if (merchantDTO.getBrandCode() == null || merchantDTO.getBrandCode().trim().isEmpty()) {
            merchantDTO.setBrandCode("NB01"); // 기본값으로 노바시네마 배정
        }
        merchantMapper.insertMerchant(merchantDTO);
    }

    // 이용내역 존재 여부에 따른 가맹점 물리 삭제 방어 로직
    public void deleteMerchant(String merchantCode) {
        try {
            merchantMapper.deleteMerchant(merchantCode);
        } catch (DataIntegrityViolationException e) {
            // FK 제약 조건 위반 (이미 이용내역이 엮여 있으면 삭제 불가 처리)
            throw new IllegalStateException("이용내역이 존재하는 가맹점은 삭제할 수 없습니다.");
        }
    }

    // 가맹점 정보 갱신 로직 (브랜드코드 유효성 검사 포함)
    public void updateMerchant(MerchantDTO merchantDTO) {
        try {
            merchantMapper.updateMerchant(merchantDTO);
        } catch (DataIntegrityViolationException e) {
            // 존재하지 않는 브랜드코드 입력 시 FK 제약조건 에러 리졸빙
            if (e.getMessage().contains("brand") || e.getMessage().contains("BRAND")) {
                throw new IllegalStateException("존재하지 않는 브랜드 코드입니다. BR001(노바시네마) 또는 BR002(바이크웨이)만 사용 가능합니다.");
            }
            throw new IllegalStateException("가맹점 수정에 실패했습니다. 입력값을 확인해주세요.");
        }
    }
}
