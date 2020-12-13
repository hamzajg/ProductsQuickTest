package com.hamzajg.quickstart.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class UpdateCustomerEndpoint {
    @PATCH
    @Path("/{customerId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateCustomerResponse update(@PathParam("customerId") String customerId, UpdateCustomerRequest req) {
        return new UpdateCustomerResponse();
    }

    public class UpdateCustomerRequest {
    }

    public class UpdateCustomerResponse {
    }
}
