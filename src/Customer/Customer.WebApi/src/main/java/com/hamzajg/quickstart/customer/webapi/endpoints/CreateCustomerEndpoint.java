package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class CreateCustomerEndpoint {

    @Inject
    CustomerServicesFacade customerServicesFacade;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateCustomerResponse create(CreateCustomer command) {
        var result = customerServicesFacade.CreateCustomer(command);
        return new CreateCustomerResponse(result.id, result.firstName, result.lastName, result.address, result.email, result.mobile);
    }

}


