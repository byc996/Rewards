package com.charter.service;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDto;

public interface TransactionService {

    /**
     * Create new transaction
     * @param transactionDto
     * @return
     */
    TransactionDto createTransaction(TransactionDto transactionDto);
}
