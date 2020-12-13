package com.hamzajg.quicktest.customer.application.usecases

import com.hamzajg.quicktest.customer.domain.entities.Customer
import com.hamzajg.quicktest.customer.domain.entities.CustomerRepository

class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customerList = new ArrayList<>()
    @Override
    Collection<Customer> getAll() {
        return customerList
    }

    @Override
    void save(Customer customer) {
        customerList.add(customer)
    }
}
