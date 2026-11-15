package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetAllProductsEndpoint {
    @Inject
    ProductServicesFacade productServicesFacade;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductsResponse getAll() {
        var result = productServicesFacade.getAllProducts();
        return new GetAllProductsResponse(result);
    }
}