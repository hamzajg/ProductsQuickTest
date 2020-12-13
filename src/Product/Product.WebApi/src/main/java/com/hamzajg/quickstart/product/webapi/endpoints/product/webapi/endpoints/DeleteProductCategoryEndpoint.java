package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class DeleteProductCategoryEndpoint {
    @DELETE
    @Path("/{productCategoryId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteProductCategoryResponse delete(@PathParam("productCategoryId") String productCategoryId) {
        return new DeleteProductCategoryResponse();
    }

    public class DeleteProductCategoryResponse {
    }
}
