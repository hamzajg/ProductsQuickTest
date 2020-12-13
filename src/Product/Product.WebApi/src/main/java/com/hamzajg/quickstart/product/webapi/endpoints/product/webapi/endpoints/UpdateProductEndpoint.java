package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class UpdateProductEndpoint {
    @PATCH
    @Path("/{productId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateProductResponse update(@PathParam("productId") String productId, UpdateProductRequest req) {
        return new UpdateProductResponse();
    }

    public class UpdateProductRequest {
    }

    public class UpdateProductResponse {
    }
}
