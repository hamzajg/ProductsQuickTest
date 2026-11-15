package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class CreateCustomerEndpoint {

    @Inject
    CustomerServicesFacade customerServicesFacade;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateCustomerResponse create(CreateCustomer command) {
        var result = customerServicesFacade.createCustomer(command);
        return new CreateCustomerResponse(result.id, result.firstName, result.lastName, result.address, result.email, result.mobile);
    }

}


