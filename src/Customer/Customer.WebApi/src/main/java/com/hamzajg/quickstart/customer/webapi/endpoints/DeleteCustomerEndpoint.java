package com.hamzajg.quickstart.customer.webapi.endpoints;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

