package com.charter.controller;

import com.charter.entity.Transaction;
import com.charter.entity.dto.TransactionDto;
import com.charter.service.TransactionService;
import org.springframework.http.HttpStatus;
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

    /**
     * Create new transaction
     * @param transactionDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto transactionDTO = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

}
