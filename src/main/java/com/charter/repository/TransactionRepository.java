package com.charter.repository;

import com.charter.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Get all transactions by customer id for the past three month
     * @param customerId
     * @param threeMonthAgo
     * @return
     */
    List<Transaction> findTransactionsByCustomerIdAndCreateTimeAfter(Long customerId, LocalDate threeMonthAgo);
}
