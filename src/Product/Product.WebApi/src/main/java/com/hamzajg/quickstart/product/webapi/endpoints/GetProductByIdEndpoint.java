package com.hamzajg.quickstart.product.webapi.endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetProductByIdEndpoint {
    @Inject
    ProductServicesFacade productServicesFacade;

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetProductByIdResponse getById(@PathParam("productId") String productId) {
        var result = productServicesFacade.getOneProductById(productId);
        return new GetProductByIdResponse(result.id, result.name, result.category, result.unitPrice, result.discount, result
                .availableQty);
    }
}
