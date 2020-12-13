package com.hamzajg.quickstart.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class CreateCustomerEndpoint {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateCustomerResponse create(CreateCustomerRequest req) {
        return new CreateCustomerResponse();
    }

    public class CreateCustomerRequest {
    }

    public class CreateCustomerResponse {
    }
}
