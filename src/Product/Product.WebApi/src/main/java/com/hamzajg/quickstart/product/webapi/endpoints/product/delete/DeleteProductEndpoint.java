package com.hamzajg.quickstart.product.webapi.endpoints.product.delete;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

