package com.hulahoop.redback.merchant.controller;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.merchant.model.dto.MerchantDTO;
import com.hulahoop.redback.merchant.model.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    // ✅ 페이징 + 검색 지원
    @GetMapping
    public ResponseEntity<PageResponseDTO<MerchantDTO>> getMerchants(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<MerchantDTO> result = merchantService.getMerchants(pageRequestDTO);
        return ResponseEntity.ok(result);
    }
}
