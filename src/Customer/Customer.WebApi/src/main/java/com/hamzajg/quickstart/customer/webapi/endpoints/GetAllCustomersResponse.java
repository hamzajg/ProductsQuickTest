package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.dtos.CustomerDto;

import java.util.Collection;

public class GetAllCustomersResponse {
    public Collection<CustomerDto> items;

    public GetAllCustomersResponse(Collection<CustomerDto> items) {

        this.items = items;
    }
}
