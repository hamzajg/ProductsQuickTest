package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetAllProductsEndpoint {
    @GET
    @Path("/{productCategoryId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductsResponse getAll() {
        return new GetAllProductsResponse();
    }

    public class GetAllProductsResponse {
    }
}
