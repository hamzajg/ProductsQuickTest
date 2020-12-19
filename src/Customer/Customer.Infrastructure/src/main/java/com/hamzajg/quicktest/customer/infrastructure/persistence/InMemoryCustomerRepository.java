package com.hamzajg.quicktest.customer.infrastructure.persistence;

import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.customer.domain.entities.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customerList = new ArrayList<>();

    @Override
    public Collection<Customer> getAll() {
        return customerList;
    }

    @Override
    public Customer getOneById(UUID id) {
        return customerList.stream().filter(c -> c.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        customerList.add(customer);
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer) {
        var exist = getOneById(newCustomer.id());
        if (exist == null)
            return null;
        customerList.remove(exist);
        customerList.add(newCustomer);
        return newCustomer;
    }
}
