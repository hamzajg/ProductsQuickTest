package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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