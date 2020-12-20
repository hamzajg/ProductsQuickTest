package com.hamzajg.quicktest.customer.domain.entities;

import java.util.Collection;
import java.util.UUID;

public interface CustomerRepository {
    Collection<Customer> getAll();

    Customer getOneById(UUID id);

    Customer save(Customer customer);

    Customer update(Customer newCustomer);

    Customer delete(UUID id);
}
