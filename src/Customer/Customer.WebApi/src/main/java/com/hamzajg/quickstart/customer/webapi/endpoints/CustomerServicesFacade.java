package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.customer.application.messaging.CustomerCommandReqResPublisher;
import com.hamzajg.quicktest.customer.infrastructure.messaging.InMemoryCustomerCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.CustomerDto;
import com.hamzajg.quicktest.sharedkernel.mappers.CustomerMapper;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerServicesFacade {
    private CustomerCommandReqResPublisher customerPublisherProvider = new InMemoryCustomerCommandReqResPublisher();
    private CustomerMapper customerMapper = new CustomerMapper();
    public CustomerDto CreateCustomer(CreateCustomer command) {
        return customerMapper.customerToCustomerDto(customerPublisherProvider.publishAndWait(command));
    }
}
