package com.hamzajg.quickstart.product.webapi.endpoints.product.delete;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class DeleteProductEndpoint {
    @DELETE
    @Path("/{productId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteProductResponse delete(@PathParam("productId") String productCategoryId) {
        return new DeleteProductResponse();
    }

}

