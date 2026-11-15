package com.hamzajg.quicktest.order.infrastructure.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * The customers service, as seen from the orders service. A missing customer
 * answers 404, which the gateway turns into "does not exist".
 */
@RegisterRestClient(configKey = "customers")
@Path("/api/v1/customers")
public interface CustomerClient {

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    CustomerView getById(@PathParam("customerId") String customerId);
}
