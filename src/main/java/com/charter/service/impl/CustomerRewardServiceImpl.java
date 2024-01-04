package com.charter.service.impl;

import com.charter.entity.Transaction;
import com.charter.repository.TransactionRepository;
import com.charter.service.CustomerRewardService;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerRewardServiceImpl implements CustomerRewardService {

    private TransactionRepository transactionRepository;

    public CustomerRewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Map<Integer, Integer> getMonthlyRewardByCustomerId(long customerId) {
        List<Transaction> transactions = transactionRepository.findTransactionsByCustomerId(customerId);
        Map<Integer, Integer> monthlyReward = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getCreateTime().getMonth(),
                        Collectors.summingInt(transaction -> calculateReward(transaction.getAmount()))));
        return monthlyReward;
    }

    @Override
    public int getTotalRewardByCustomerId(long customerId) {
        List<Transaction> transactions = transactionRepository.findTransactionsByCustomerId(customerId);
        int reward = 0;
        for (Transaction transaction : transactions) {
            reward += calculateReward(transaction.getAmount());
        }
        return reward;
    }


    private int calculateReward(int amount) {
        int reward = 0;

        if (amount > 100) {
            reward += (amount - 100) * 2;
        }

        if (amount > 50) {
            reward += (amount - 50);
        }

        return reward;
    }
}
