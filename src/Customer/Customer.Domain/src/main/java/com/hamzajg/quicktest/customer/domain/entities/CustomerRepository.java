package com.hamzajg.quicktest.customer.domain.entities;

import java.util.Collection;

public interface CustomerRepository {
    Collection<Customer> getAll();

    void save(Customer customer);
}
