package com.charter.controller;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDTO;
import com.charter.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction transaction) {
        TransactionDTO transactionDTO = transactionService.addTransaction(transaction);
        return ResponseEntity.ok(transactionDTO);
    }

}
