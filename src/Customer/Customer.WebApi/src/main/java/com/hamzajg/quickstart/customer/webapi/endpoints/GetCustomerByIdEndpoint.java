package com.hamzajg.quickstart.customer.webapi.endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/customers")
public class GetCustomerByIdEndpoint {
    @Inject
    CustomerServicesFacade customerServicesFacade;
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetCustomerByIdResponse getById(@PathParam("customerId") String customerId) {
        var result = customerServicesFacade.getOneCustomerById(customerId);
        return new GetCustomerByIdResponse(result.id, result.firstName, result.lastName, result.address, result.email, result.mobile);
    }

}

