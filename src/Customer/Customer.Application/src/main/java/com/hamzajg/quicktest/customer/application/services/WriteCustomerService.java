package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateCustomer;

public interface WriteCustomerService {
    Customer createCustomer(CreateCustomer command);
    Customer updateCustomer(UpdateCustomer command);
}
