package com.hamzajg.quickstart.product.webapi.endpoints.product.category.update;

import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProductCategory;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class UpdateProductCategoryEndpoint {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;
    @PATCH
    @Path("/{productCategoryId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateProductCategoryResponse update(@PathParam("productCategoryId") String productCategoryId,
                                                UpdateProductCategoryRequest req) {
        var result = productCategoryServicesFacade.updateProductCategory(new UpdateProductCategory(productCategoryId, req.name));
        return new UpdateProductCategoryResponse(result.id, result.name);
    }
}

