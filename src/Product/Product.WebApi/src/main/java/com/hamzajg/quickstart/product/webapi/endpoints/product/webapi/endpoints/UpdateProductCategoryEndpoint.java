package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class UpdateProductCategoryEndpoint {
    @PATCH
    @Path("/{productCategoryId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateProductCategoryResponse update(@PathParam("productCategoryId") String productCategoryId, UpdateProductCategoryRequest req) {
        return new UpdateProductCategoryResponse();
    }

    public class UpdateProductCategoryRequest {
    }

    public class UpdateProductCategoryResponse {
    }
}
