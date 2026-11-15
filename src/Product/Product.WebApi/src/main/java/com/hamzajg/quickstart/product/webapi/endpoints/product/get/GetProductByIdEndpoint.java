package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
