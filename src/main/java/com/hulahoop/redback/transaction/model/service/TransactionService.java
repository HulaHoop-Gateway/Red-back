package com.hulahoop.redback.transaction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulahoop.redback.transaction.model.dao.TransactionMapper;
import com.hulahoop.redback.transaction.model.dto.TransactionDTO;

@Service
public class TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    // 🔹 전체 거래 내역 조회
    public List<TransactionDTO> getAllTransactions() {
        return transactionMapper.selectAllTransactions();
    }

    // 🔥 영화 예매 시 트랜잭션 저장
    public int insertTransaction(TransactionDTO dto) {
        return transactionMapper.insertTransaction(dto);
    }
}
