package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class GetProductCategoryByIdEndpoint {
    @GET
    @Path("/{productCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetProductCategoryByIdResponse getById(@PathParam("productCategoryId") String productCategoryId) {
        return new GetProductCategoryByIdResponse();
    }

    public class GetProductCategoryByIdResponse {
    }
}
