package com.hamzajg.quickstart.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class GetCustomerByIdEndpoint {
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetCustomerByIdResponse getById(@PathParam("customerId") String customerId) {
        return new GetCustomerByIdResponse();
    }

    public class GetCustomerByIdResponse {
    }
}
