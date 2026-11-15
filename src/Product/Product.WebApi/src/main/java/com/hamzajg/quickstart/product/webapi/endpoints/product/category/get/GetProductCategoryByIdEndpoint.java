package com.hamzajg.quickstart.product.webapi.endpoints.product.category.get;


import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class GetProductCategoryByIdEndpoint {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @GET
    @Path("/{productCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetProductCategoryByIdResponse getById(@PathParam("productCategoryId") String productCategoryId) {
        var result = productCategoryServicesFacade.getOneProductCategoryById(productCategoryId);
        return new GetProductCategoryByIdResponse(result.id, result.name);
    }

}

