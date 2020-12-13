package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetProductByIdEndpoint {
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetProductByIdResponse getById(@PathParam("productId") String productId) {
        return new GetProductByIdResponse();
    }

    public class GetProductByIdResponse {
    }
}
