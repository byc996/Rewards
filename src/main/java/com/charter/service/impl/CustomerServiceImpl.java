package com.charter.service.impl;

import com.charter.entity.Customer;
import com.charter.entity.Transaction;
import com.charter.entity.dto.CustomerDTO;
import com.charter.entity.dto.TransactionDTO;
import com.charter.repository.CustomerRepository;
import com.charter.repository.TransactionRepository;
import com.charter.service.CustomerService;
import com.charter.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(savedCustomer, customerDTO);
        return customerDTO;
    }
}
