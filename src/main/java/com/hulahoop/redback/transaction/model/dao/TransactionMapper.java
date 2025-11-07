package com.hulahoop.redback.transaction.model.dao;

import java.util.List;
import com.hulahoop.redback.transaction.model.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    List<TransactionDTO> selectAllTransactions();
}
