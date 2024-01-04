package com.charter.repository;

import com.charter.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;


    @BeforeEach
    void setUp() {
        // mock data
        Transaction transaction1 = Transaction.builder()
                .id(1L).amount(55).customerId(1L).createTime(LocalDate.of(2023, 11, 6))
                .build();
        transactionRepository.save(transaction1);
        Transaction transaction2 = Transaction.builder()
                .id(2L).amount(105).customerId(1L).createTime(LocalDate.of(2023, 12, 16))
                .build();
        transactionRepository.save(transaction2);

        Transaction transaction3 = Transaction.builder()
                .id(2L).amount(105).customerId(1L).createTime(LocalDate.of(2023, 10, 1))
                .build();
        transactionRepository.save(transaction2);
    }

    @Test
    void testFindTransactionsByCustomerId() {
        LocalDate localDate = LocalDate.now().minusMonths(3);
        List<Transaction> transactions = transactionRepository.findTransactionsByCustomerIdAndCreateTimeAfter(1L, localDate);
        assertEquals(2, transactions.size());
    }
}