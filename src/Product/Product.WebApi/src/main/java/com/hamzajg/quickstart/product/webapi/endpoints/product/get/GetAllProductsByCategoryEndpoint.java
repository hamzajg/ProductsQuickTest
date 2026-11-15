package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class GetAllProductsByCategoryEndpoint {
    @Inject
    ProductServicesFacade productServicesFacade;

    @GET
    @Path("/{productCategoryId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductsByCategoryResponse getAllByCategory(@PathParam("productCategoryId") String productCategoryId) {
        var result = productServicesFacade.getAllProductsByCategoryId(productCategoryId);
        return new GetAllProductsByCategoryResponse(result);
    }
}