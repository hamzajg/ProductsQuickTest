package com.hamzajg.quickstart.customer.webapi.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class DeleteCustomerEndpoint {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @DELETE
    @Path("/{customerId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteCustomerResponse delete(@PathParam("customerId") String customerId) {
        var result = customerServicesFacade.deleteCustomer(customerId);
        return new DeleteCustomerResponse(result);
    }

}

