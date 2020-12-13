package com.hamzajg.quicktest.customer.application;

import com.hamzajg.quicktest.customer.domain.entities.CustomerRepository;

public class UnitOfWork {

    private final CustomerRepository customerRepository;

    public UnitOfWork(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerRepository customerRepository() {
        return customerRepository;
    }
}
