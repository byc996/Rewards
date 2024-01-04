package com.charter.service.impl;

import com.charter.entity.Transaction;
import com.charter.repository.TransactionRepository;
import com.charter.service.RewardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    private TransactionRepository transactionRepository;

    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Map<Integer, Integer> getMonthlyRewardByCustomerId(long customerId) {
        LocalDate localDate = LocalDate.now().minusMonths(3);
        // Get transactions for the last three months
        List<Transaction> transactions = transactionRepository
                .findTransactionsByCustomerIdAndCreateTimeAfter(customerId, localDate);
        return transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getCreateTime().getMonthValue(),
                        Collectors.summingInt(transaction -> calculateReward(transaction.getAmount()))));
    }

    @Override
    public int getTotalRewardByCustomerId(long customerId) {
        LocalDate localDate = LocalDate.now().minusMonths(3);
        // Get transactions for the last three months
        List<Transaction> transactions = transactionRepository
                .findTransactionsByCustomerIdAndCreateTimeAfter(customerId, localDate);
        int reward = 0;
        for (Transaction transaction : transactions) {
            reward += calculateReward(transaction.getAmount());
        }
        return reward;
    }


    /**
     * Calculate the reward points based on purchase
     * @param amount
     * @return
     */
    private int calculateReward(int amount) {
        int reward = 0;

        if (amount > 100) {
            reward += (amount - 100) * 2;
            amount = 100;
        }

        if (amount > 50) {
            reward += (amount - 50);
        }

        return reward;
    }
}
