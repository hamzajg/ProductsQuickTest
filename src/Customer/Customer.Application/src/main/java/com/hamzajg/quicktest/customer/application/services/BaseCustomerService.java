package com.hamzajg.quicktest.customer.application.services;

import com.hamzajg.quicktest.customer.application.usecases.*;
import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class BaseCustomerService implements WriteCustomerService, ReadCustomerService {
    private CreateCustomerUseCase createCustomerUseCase;
    private UpdateCustomerUseCase updateCustomerUseCase;
    private DeleteCustomerUseCase deleteCustomerUseCase;
    private GetAllCustomersUseCase getAllCustomersUseCase;
    private GetCustomerByIdUseCase getCustomerByIdUseCase;

    @Inject
    public BaseCustomerService(CreateCustomerUseCase createCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    public BaseCustomerService(GetAllCustomersUseCase getAllCustomersUseCase, GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    @Override
    public Customer createCustomer(CreateCustomer command) {
        return createCustomerUseCase.execute(command.firstName, command.lastName, command.address, command.email, command.mobile);
    }

    @Override
    public Customer updateCustomer(UpdateCustomer command) {
        return updateCustomerUseCase.execute(UUID.fromString(command.customerId), command.firstName, command.lastName, command.address, command.email, command.mobile);
    }

    @Override
    public Customer deleteCustomer(DeleteCustomer command) {
        return deleteCustomerUseCase.execute(UUID.fromString(command.customerId));
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return getAllCustomersUseCase.execute();
    }

    @Override
    public Customer getOneCustomerById(UUID id) {
        return getCustomerByIdUseCase.execute(id);
    }
}
