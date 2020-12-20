package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.customer.application.messaging.CustomerCommandReqResPublisher;
import com.hamzajg.quicktest.customer.application.services.BaseCustomerService;
import com.hamzajg.quicktest.customer.application.services.ReadCustomerService;
import com.hamzajg.quicktest.customer.application.usecases.GetAllCustomersUseCase;
import com.hamzajg.quicktest.customer.application.usecases.GetCustomerByIdUseCase;
import com.hamzajg.quicktest.customer.infrastructure.messaging.CreateCustomerHandler;
import com.hamzajg.quicktest.customer.infrastructure.messaging.InMemoryCustomerCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.CustomerDto;
import com.hamzajg.quicktest.sharedkernel.mappers.CustomerMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateCustomer;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class CustomerServicesFacade {
    private CustomerCommandReqResPublisher customerPublisherProvider = new InMemoryCustomerCommandReqResPublisher();
    private CustomerMapper customerMapper = new CustomerMapper();
    private ReadCustomerService readCustomerService = new BaseCustomerService(new GetAllCustomersUseCase(InMemoryCustomerCommandReqResPublisher.unitOfWork),
            new GetCustomerByIdUseCase(InMemoryCustomerCommandReqResPublisher.unitOfWork));

    public CustomerDto createCustomer(CreateCustomer command) {
        return customerMapper.customerToCustomerDto(customerPublisherProvider.publishAndWait(command));
    }

    public Collection<CustomerDto> getAllCustomers() {
        return customerMapper.customersToCustomersDto(readCustomerService.getAllCustomers());
    }

    public CustomerDto getOneCustomerById(String customerId) {
        return customerMapper.customerToCustomerDto(readCustomerService.getOneCustomerById(UUID.fromString(customerId)));
    }

    public CustomerDto updateCustomer(UpdateCustomer command) {
        return customerMapper.customerToCustomerDto(customerPublisherProvider.publishAndWait(command));
    }

    public String deleteCustomer(String customerId) {
        var result = customerPublisherProvider.publishAndWait(new DeleteCustomer(customerId));
        if (result == null)
            return null;
        return result.id().toString();
    }
}
