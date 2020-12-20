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
    Customer getOneById(UUID id) {
        return null
    }

    @Override
    Customer save(Customer customer) {
        customerList.add(customer)
        return customer
    }

    @Override
    Customer update(Customer newCustomer) {
        return null
    }

    @Override
    Customer delete(UUID id) {
        var exist = getOneById(id);
        if (exist == null)
            return null;
        customerList.remove(exist);
        return exist;
    }
}
