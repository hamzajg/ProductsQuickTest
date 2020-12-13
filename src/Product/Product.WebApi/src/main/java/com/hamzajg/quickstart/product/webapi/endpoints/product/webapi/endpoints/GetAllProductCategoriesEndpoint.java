package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class GetAllProductCategoriesEndpoint {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductCategoriesResponse getAll() {
        return new GetAllProductCategoriesResponse();
    }

    public class GetAllProductCategoriesResponse {
    }
}
