package com.hamzajg.quicktest.sharedkernel.mappers;

import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.dtos.CustomerDto;

public class CustomerMapper {
    public CustomerDto customerToCustomerDto(Customer customer) {
        if(customer == null)
        return null;
        var dto = new CustomerDto();
        dto.id = customer.id().toString();
        dto.firstName = customer.firstName();
        dto.lastName = customer.lastName();
        dto.address = customer.address();
        dto.email = customer.email();
        dto.mobile = customer.mobile();
        return dto;
    }
}
