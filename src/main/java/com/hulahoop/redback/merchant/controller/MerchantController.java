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

    // 페이징 및 검색 동시 지원
    @GetMapping
    public ResponseEntity<PageResponseDTO<MerchantDTO>> getMerchants(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<MerchantDTO> result = merchantService.getMerchants(pageRequestDTO);
        return ResponseEntity.ok(result);
    }

    // 신규 가맹점 생성 엔드포인트
    @PostMapping
    public ResponseEntity<String> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        merchantService.createMerchant(merchantDTO);
        return ResponseEntity.ok("가맹점이 생성되었습니다.");
    }

    // 가맹점 삭제 필터링 로직
    @DeleteMapping("/{merchantCode}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String merchantCode) {
        try {
            merchantService.deleteMerchant(merchantCode);
            return ResponseEntity.ok("가맹점이 삭제되었습니다.");
        } catch (IllegalStateException e) {
            // 이미 이용내역이 엮여있는 가맹점에 대한 방어 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 그 외 알 수 없는 통신 오류
            return ResponseEntity.badRequest().body("삭제에 실패했습니다.");
        }
    }

    // 기존 가맹점 정보 덮어쓰기 로직
    @PutMapping("/{merchantCode}")
    public ResponseEntity<String> updateMerchant(@PathVariable String merchantCode,
            @RequestBody MerchantDTO merchantDTO) {
        try {
            merchantDTO.setMerchantCode(merchantCode); // 경로 변수에서 넘어온 식별자를 DTO에 명시적으로 덮어씌움
            merchantService.updateMerchant(merchantDTO);
            return ResponseEntity.ok("가맹점이 성공적으로 수정되었습니다.");
        } catch (IllegalStateException e) {
            // 존재하지 않는 브랜드 코드 입력 등의 도메인 에러 통제
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 그 외 알 수 없는 에러
            return ResponseEntity.badRequest().body("가맹점 수정에 실패했습니다.");
        }
    }
}
