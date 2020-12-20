package com.hamzajg.quickstart.product.webapi.endpoints.product.category.delete;


import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

