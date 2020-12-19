package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.domain.entities.Customer;

import java.util.Collection;
import java.util.UUID;

public interface ReadCustomerService {
    Collection<Customer> getAllCustomers();

    Customer getOneCustomerById(UUID id);
}
