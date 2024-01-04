package com.charter.service;

import com.charter.entity.Customer;
import com.charter.entity.Transaction;
import com.charter.entity.dto.CustomerDTO;
import com.charter.entity.dto.TransactionDTO;

public interface CustomerService {

    CustomerDTO addCustomer(Customer customer);
}
