package com.hulahoop.redback.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.transaction.model.dto.TransactionDTO;
import com.hulahoop.redback.transaction.model.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // 기존 전체 목록 조회 엔드포인트 호환성 유지
    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // 신규 스펙 적용 (페이징, 정렬, 필터 통합 지원 제공)
    @GetMapping
    public ResponseEntity<PageResponseDTO<TransactionDTO>> getTransactions(PageRequestDTO pageRequestDTO) {

        PageResponseDTO<TransactionDTO> result = transactionService.getTransactions(pageRequestDTO);

        return ResponseEntity.ok(result);
    }

    // 하위 호환을 위한 기존 결제 내역 단건 인서트 API 지속 제공
    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO dto) {
        try {
            transactionService.insertTransaction(dto);
            return ResponseEntity.ok(Map.of("message", "success", "transaction", dto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "error", "details", e.getMessage()));
        }
    }
}
