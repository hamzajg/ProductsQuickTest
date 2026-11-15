package com.hamzajg.quickstart.product.webapi.endpoints.product.update;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProduct;

import jakarta.inject.Inject;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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

