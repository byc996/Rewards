package com.charter.service;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDto;
import com.charter.repository.TransactionRepository;
import com.charter.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testAddTransaction() {
        Transaction transaction = Transaction.builder().amount(65).customerId(1L).build();
        TransactionDto transactionDto = TransactionDto.builder().amount(65).customerId(1L).build();
        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        TransactionDto savedTransactionDto = transactionService.createTransaction(transactionDto);
        assertNotNull(savedTransactionDto);
        assertEquals(65, savedTransactionDto.getAmount());
    }
}