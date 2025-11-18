package com.hulahoop.redback.transaction.model.dao;

import java.util.List;

import com.hulahoop.redback.transaction.model.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    // 🔹 전체 거래 내역 조회
    List<TransactionDTO> selectAllTransactions();

    // 🔥 영화 예매 완료 시 거래 저장
    int insertTransaction(TransactionDTO dto);
}
