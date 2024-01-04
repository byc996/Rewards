package com.charter.service;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDTO;

public interface TransactionService {

    TransactionDTO addTransaction(Transaction transaction);
}
