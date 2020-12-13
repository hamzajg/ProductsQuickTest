package com.hamzajg.quickstart.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class GetAllCustomersEndpoint {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllCustomersResponse getAll() {
        return new GetAllCustomersResponse();
    }

    public class GetAllCustomersResponse {
    }
}
