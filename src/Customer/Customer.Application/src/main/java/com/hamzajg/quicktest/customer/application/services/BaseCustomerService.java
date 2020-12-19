package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.application.usecases.CreateCustomerUseCase;
import com.hamzajg.quicktest.customer.application.usecases.GetAllCustomersUseCase;
import com.hamzajg.quicktest.customer.application.usecases.GetCustomersByIdUseCase;
import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class BaseCustomerService implements WriteCustomerService, ReadCustomerService {
    private CreateCustomerUseCase createCustomerUseCase;
    private GetAllCustomersUseCase getAllCustomersUseCase;
    private GetCustomersByIdUseCase getCustomersByIdUseCase;

    @Inject
    public BaseCustomerService(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    public BaseCustomerService(GetAllCustomersUseCase getAllCustomersUseCase, GetCustomersByIdUseCase getCustomersByIdUseCase) {
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getCustomersByIdUseCase = getCustomersByIdUseCase;
    }

    @Override
    public Customer createCustomer(CreateCustomer command) {
        return createCustomerUseCase.execute(command.firstName, command.lastName, command.address, command.email, command.mobile);
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return getAllCustomersUseCase.execute();
    }
}
