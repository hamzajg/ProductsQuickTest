package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.application.usecases.CreateCustomerUseCase;
import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BaseCustomerService implements WriteCustomerService {
    private CreateCustomerUseCase createCustomerUseCase;

    @Inject
    public BaseCustomerService(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @Override
    public Customer createCustomer(CreateCustomer command) {
        return createCustomerUseCase.execute(command.firstName, command.lastName, command.address, command.email, command.mobile);
    }
}
