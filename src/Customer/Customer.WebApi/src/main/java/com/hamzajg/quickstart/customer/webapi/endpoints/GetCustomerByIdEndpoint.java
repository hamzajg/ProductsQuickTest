package com.hamzajg.quickstart.customer.webapi.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/customers")
public class GetCustomerByIdEndpoint {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("customerId") String customerId) {
        var result = customerServicesFacade.getOneCustomerById(customerId);
        if (result == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(new GetCustomerByIdResponse(result.id, result.firstName, result.lastName,
                result.address, result.email, result.mobile)).build();
    }

}
