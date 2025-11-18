package com.hulahoop.redback.transaction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hulahoop.redback.transaction.model.dto.TransactionDTO;
import com.hulahoop.redback.transaction.model.service.TransactionService;
import com.hulahoop.redback.member.model.dao.MemberMapper;
import com.hulahoop.redback.merchant.model.dao.MerchantMapper;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MerchantMapper merchantMapper;   // ⭐ 추가됨

    // 🔹 전체 거래 내역 조회
    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // 🔥 새로운 거래(영화 예매 등) 기록 추가 API
    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO dto) {

        try {
            // ============================================================
            // 1) phone_number → member_code 변환
            // ============================================================
            String memberCode = memberMapper.findMemberCodeByPhone(dto.getPhoneNum());
            if (memberCode == null) {
                return ResponseEntity.status(400).body(Map.of(
                        "message", "error",
                        "details", "해당 전화번호로 회원을 찾을 수 없습니다."
                ));
            }
            dto.setMemberCode(memberCode);


            // ============================================================
            // 2) branch_name → merchant_code 변환
            //
            // 영화 서버에서는 merchant_code 에 "노바시네마 하남1점" 이런 branch_name 이 들어옴.
            // 하지만 관리자 DB에는 실제 merchant_code (예: M000000001) 를 저장해야 함.
            // ============================================================
            String realMerchantCode = merchantMapper.findMerchantCodeByName(dto.getMerchantCode());
            if (realMerchantCode == null) { // 가맹점 이름 매칭 실패
                return ResponseEntity.status(400).body(Map.of(
                        "message", "error",
                        "details", "해당 가맹점(branch_name)이 관리자 DB에 존재하지 않습니다: " + dto.getMerchantCode()
                ));
            }
            dto.setMerchantCode(realMerchantCode);


            // ============================================================
            // 3) DB insert 실행
            // ============================================================
            transactionService.insertTransaction(dto);


            return ResponseEntity.ok(Map.of(
                    "message", "success",
                    "transaction", dto
            ));

        } catch (Exception e) {

            return ResponseEntity.status(500).body(Map.of(
                    "message", "error",
                    "details", e.getMessage()
            ));
        }
    }
}
