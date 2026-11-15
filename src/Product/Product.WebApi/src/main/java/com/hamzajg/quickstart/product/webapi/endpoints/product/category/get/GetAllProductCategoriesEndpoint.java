package com.hamzajg.quickstart.product.webapi.endpoints.product.category.get;


import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class GetAllProductCategoriesEndpoint {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllProductCategoriesResponse getAll() {
        var result = productCategoryServicesFacade.getAllProductCategories();
        return new GetAllProductCategoriesResponse(result);
    }

}

