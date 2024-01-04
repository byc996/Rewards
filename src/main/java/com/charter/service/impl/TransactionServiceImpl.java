package com.charter.service.impl;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDTO;
import com.charter.repository.TransactionRepository;
import com.charter.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO addTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionDTO transactionDTO = new TransactionDTO();
        BeanUtils.copyProperties(savedTransaction, transactionDTO);
        return transactionDTO;
    }
}
