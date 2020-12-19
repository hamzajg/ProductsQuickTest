package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.customer.application.messaging.CustomerCommandReqResPublisher;
import com.hamzajg.quicktest.customer.application.services.BaseCustomerService;
import com.hamzajg.quicktest.customer.application.services.ReadCustomerService;
import com.hamzajg.quicktest.customer.application.usecases.GetAllCustomersUseCase;
import com.hamzajg.quicktest.customer.application.usecases.GetCustomersByIdUseCase;
import com.hamzajg.quicktest.customer.infrastructure.messaging.CreateCustomerHandler;
import com.hamzajg.quicktest.customer.infrastructure.messaging.InMemoryCustomerCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.CustomerDto;
import com.hamzajg.quicktest.sharedkernel.mappers.CustomerMapper;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class CustomerServicesFacade {
    private CustomerCommandReqResPublisher customerPublisherProvider = new InMemoryCustomerCommandReqResPublisher();
    private CustomerMapper customerMapper = new CustomerMapper();
    private ReadCustomerService readCustomerService = new BaseCustomerService(new GetAllCustomersUseCase(CreateCustomerHandler.unitOfWork),
            new GetCustomersByIdUseCase(CreateCustomerHandler.unitOfWork));
    public CustomerDto CreateCustomer(CreateCustomer command) {
        return customerMapper.customerToCustomerDto(customerPublisherProvider.publishAndWait(command));
    }

    public Collection<CustomerDto> getAllCustomers() {
        return customerMapper.customersToCustomersDto(readCustomerService.getAllCustomers());
    }
}
