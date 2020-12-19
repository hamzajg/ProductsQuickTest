package com.hamzajg.quickstart.product.webapi.endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

