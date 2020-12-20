package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import com.hamzajg.quicktest.customer.domain.entities.Customer;

public class DeleteCustomerResponse implements Response {
    private Customer customer;

    public DeleteCustomerResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
