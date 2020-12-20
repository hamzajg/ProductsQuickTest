package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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