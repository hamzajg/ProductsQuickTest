package com.hamzajg.quickstart.customer.webapi.endpoints;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class GetAllCustomersEndpoint {
    @Inject
    CustomerServicesFacade customerServicesFacade;
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllCustomersResponse getAll() {
        var result = customerServicesFacade.getAllCustomers();
        return new GetAllCustomersResponse(result);
    }

}

