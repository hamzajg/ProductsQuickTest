package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateCustomer;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class UpdateCustomerEndpoint {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @PATCH
    @Path("/{customerId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateCustomerResponse update(@PathParam("customerId") String customerId, UpdateCustomerRequest req) {
        var result = customerServicesFacade.updateCustomer(new UpdateCustomer(customerId, req.firstName,
                req.lastName, req.address, req.email, req.mobile));
        return new UpdateCustomerResponse(result.id, result.firstName, result.lastName, result.address, result.email, result.mobile);
    }

}

