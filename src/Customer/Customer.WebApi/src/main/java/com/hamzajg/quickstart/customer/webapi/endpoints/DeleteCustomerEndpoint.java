package com.hamzajg.quickstart.customer.webapi.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class DeleteCustomerEndpoint {

    @DELETE
    @Path("/{customerId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteCustomerResponse delete(@PathParam("customerId") String customerId) {
        return new DeleteCustomerResponse();
    }

    public class DeleteCustomerResponse {
    }
}
