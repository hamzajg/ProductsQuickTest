package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetAllProductsByCategoryEndpoint {
    @GET
    @Path("/{productCategoryId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductsVyCategoryResponse getAllByCategory(@PathParam("productCategoryId") String productCategoryId) {
        return new GetAllProductsVyCategoryResponse();
    }

    public class GetAllProductsVyCategoryResponse {
    }
}
