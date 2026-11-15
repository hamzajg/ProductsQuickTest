package com.hamzajg.quickstart.product.webapi.endpoints.product.category.delete;


import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class DeleteProductCategoryEndpoint {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @DELETE
    @Path("/{productCategoryId}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteProductCategoryResponse delete(@PathParam("productCategoryId") String productCategoryId) {
        var result = productCategoryServicesFacade.deleteProductCategory(productCategoryId);
        return new DeleteProductCategoryResponse(result);
    }

}

