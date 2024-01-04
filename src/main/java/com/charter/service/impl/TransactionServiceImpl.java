package com.charter.service.impl;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDto;
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
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionDto savedTransactionDTO = new TransactionDto();
        BeanUtils.copyProperties(savedTransaction, savedTransactionDTO);
        return savedTransactionDTO;
    }
}
