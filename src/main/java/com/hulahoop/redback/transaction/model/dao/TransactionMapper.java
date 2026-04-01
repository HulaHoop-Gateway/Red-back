package com.hulahoop.redback.transaction.model.dao;

import java.util.List;

import com.hulahoop.redback.common.paging.dto.PageRequestDTO;
import com.hulahoop.redback.transaction.model.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;

// 결제 내역(T_TransactionHistory) CRUD 전용 MyBatis 매퍼
@Mapper
public interface TransactionMapper {

    // limit 없는 전체 결제 내역 조회 (하위 호환용)
    List<TransactionDTO> selectAllTransactions();

    // 페이징 + 정렬(num/amount/date) + 날짜 단위 필터 지원 조회
    List<TransactionDTO> selectTransactionsPaged(PageRequestDTO requestDTO);

    // 페이징네이션을 위한 전체 결제 건수 집계 (조회 조건 동일 적용)
    long countTransactions(PageRequestDTO requestDTO);

    // 인쇼트 가능, 실제 블맨드별 결제시 정말 사용할 수 있는 뽈지
    int insertTransaction(TransactionDTO dto);
}
