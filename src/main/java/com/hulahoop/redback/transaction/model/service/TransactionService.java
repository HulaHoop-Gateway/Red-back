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

    public List<TransactionDTO> getAllTransactions() {
        return transactionMapper.selectAllTransactions();
    }
}
