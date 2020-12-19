package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProduct;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class UpdateProductEndpoint {
    @Inject
    ProductServicesFacade productServicesFacade;

    @PATCH
    @Path("/{productId}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateProductResponse update(@PathParam("productId") String productId, UpdateProductRequest req) {
        var result = productServicesFacade.updateProduct(new UpdateProduct(productId, req.categoryId, req.name, req.unitPrice
                , req.discount, req.availableQty));
        return new UpdateProductResponse(result.id, result.name, result.category, result.unitPrice, result.discount, result
                .availableQty);
    }

}

