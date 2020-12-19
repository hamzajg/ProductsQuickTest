package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

public interface WriteCustomerService {
    Customer createCustomer(CreateCustomer command);
}
