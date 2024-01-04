package com.charter.controller;

import com.charter.entity.Customer;
import com.charter.entity.Transaction;
import com.charter.entity.dto.CustomerDTO;
import com.charter.entity.dto.TransactionDTO;
import com.charter.service.CustomerService;
import com.charter.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer) {
        CustomerDTO customerDTO = customerService.addCustomer(customer);
        return ResponseEntity.ok(customerDTO);
    }
}
