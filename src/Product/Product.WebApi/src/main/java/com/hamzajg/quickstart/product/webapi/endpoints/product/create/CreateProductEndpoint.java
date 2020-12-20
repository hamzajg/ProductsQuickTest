package com.hamzajg.quickstart.product.webapi.endpoints.product.create;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class CreateProductEndpoint {

    @Inject
    ProductServicesFacade productServicesFacade;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateProductResponse create(CreateProduct command) {
        var result = productServicesFacade.createProduct(command);
        return new CreateProductResponse(result.id, result.name, result.category, result.unitPrice, result.discount, result
                .availableQty);
    }
}


