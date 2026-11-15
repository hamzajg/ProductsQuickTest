package com.hamzajg.quicktest.order.infrastructure.clients;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * The products service, as seen from the orders service. {@code reserve} is the
 * hold a checkout takes; it answers 404 for an unknown product and 409 when
 * there is not enough stock.
 */
@RegisterRestClient(configKey = "products")
@Path("/api/v1/products")
public interface ProductClient {

    @POST
    @Path("/{productId}/reserve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    StockView reserve(@PathParam("productId") String productId, StockChangeBody body);

    @POST
    @Path("/{productId}/release")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    StockView release(@PathParam("productId") String productId, StockChangeBody body);

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductView getById(@PathParam("productId") String productId);
}
