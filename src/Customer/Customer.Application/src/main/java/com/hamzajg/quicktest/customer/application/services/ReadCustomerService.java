package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.domain.entities.Customer;

import java.util.Collection;

public interface ReadCustomerService {
    Collection<Customer> getAllCustomers();
}
