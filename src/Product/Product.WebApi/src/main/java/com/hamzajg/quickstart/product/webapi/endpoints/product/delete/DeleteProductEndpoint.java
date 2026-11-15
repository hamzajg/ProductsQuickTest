package com.hamzajg.quickstart.product.webapi.endpoints.product.delete;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class DeleteProductEndpoint {
    @Inject
    ProductServicesFacade productServicesFacade;

    @DELETE
    @Path("/{productId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteProductResponse delete(@PathParam("productId") String productId) {
        var result = productServicesFacade.deleteProduct(productId);
        return new DeleteProductResponse(result);
    }

}

