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

        List<MerchantDTO> list =
                merchantMapper.selectMerchantsPaged(requestDTO);

        long total =
                merchantMapper.countMerchants(requestDTO);

        return new PageResponseDTO<>(
                list,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total
        );
    }

    // ✅ 기존 기능 유지
    public String findMerchantCodeByName(String merchantName) {
        return merchantMapper.findMerchantCodeByName(merchantName);
    }
}
