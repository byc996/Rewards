package com.charter.service;

import com.charter.entity.Transaction;
import com.charter.repository.TransactionRepository;
import com.charter.service.impl.RewardServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RewardServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    private static List<Transaction> transactions = new ArrayList<>();


    /**
     * Prepare mock data
     */
    @BeforeAll
    public static void init() {
        Transaction transaction1 = Transaction.builder()
                .amount(55).customerId(1L).createTime(LocalDate.of(2023, 11, 1)).build();
        transactions.add(transaction1);
        Transaction transaction2 = Transaction.builder()
                .amount(111).customerId(1L).createTime(LocalDate.of(2023, 11, 12)).build();
        transactions.add(transaction2);
        Transaction transaction3 = Transaction.builder()
                .amount(67).customerId(1L).createTime(LocalDate.of(2023, 12, 2)).build();
        transactions.add(transaction3);
        Transaction transaction4 = Transaction.builder()
                .amount(50).customerId(1L).createTime(LocalDate.of(2023, 10, 28)).build();
        transactions.add(transaction4);
        Transaction transaction5 = Transaction.builder()
                .amount(33).customerId(1L).createTime(LocalDate.of(2023, 9, 8)).build();
        transactions.add(transaction5);
    }

    @Test
    void testGetMonthlyRewardByCustomerId() {
        when(transactionRepository.findTransactionsByCustomerIdAndCreateTimeAfter(Mockito.any(Long.class),
                Mockito.any(LocalDate.class))).thenReturn(transactions);
        Map<Integer, Integer> monthlyReward = rewardService.getMonthlyRewardByCustomerId(1L);
//        System.out.println(monthlyReward);
        /**
         * 50: 50 - 50 = 0
         * October reward: = 0
         */
        assertEquals(0, monthlyReward.get(10));
        /**
         * 55: 55 - 50 = 5
         * 111: (111 - 100) * 2 + (100 - 50) = 72
         * November reward: = 5 + 72 = 77
         */
        assertEquals(77, monthlyReward.get(11));
        /**
         * 67: 67 - 50 = 17
         * December reward: 17 + 108 = 17
         */
        assertEquals(17, monthlyReward.get(12));
    }

    @Test
    void testGetTotalRewardByCustomerId() {
        when(transactionRepository.findTransactionsByCustomerIdAndCreateTimeAfter(Mockito.any(Long.class),
                Mockito.any(LocalDate.class))).thenReturn(transactions);
        int totalReward = rewardService.getTotalRewardByCustomerId(1L);
//        System.out.println(totalReward);
        /**
         * 55: 55 - 50 = 5
         * 111: (111 - 100) * 2 + (100 - 50) = 72
         * 67: 67 - 50 = 17
         * 50: 50 - 50 = 0
         * total = 5 + 72 + 17 = 94
         */
        assertEquals(94, totalReward);
    }
}