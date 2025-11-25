package com.hulahoop.redback.transaction.model.service;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.common.paging.dto.PageResponseDTO;
import com.hulahoop.redback.transaction.model.dao.TransactionMapper;
import com.hulahoop.redback.transaction.model.dto.TransactionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    // 기존 전체 조회 유지
    public List<TransactionDTO> getAllTransactions() {
        return transactionMapper.selectAllTransactions();
    }

    // 신규: 페이징 + 정렬 + 날짜 필터 + merchant 필터 대응
    public PageResponseDTO<TransactionDTO> getTransactions(PageRequestDTO requestDTO) {

        List<TransactionDTO> list =
                transactionMapper.selectTransactionsPaged(requestDTO);

        long total =
                transactionMapper.countTransactions(requestDTO);

        return new PageResponseDTO<>(
                list,
                requestDTO.getPage(),
                requestDTO.getSize(),
                total
        );
    }

    // 기존 insert 유지
    public int insertTransaction(TransactionDTO dto) {
        return transactionMapper.insertTransaction(dto);
    }
}
