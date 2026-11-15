package com.hamzajg.quickstart.product.webapi.endpoints.product.category.create;

import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class CreateProductCategoryEndpoint {

    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateProductCategoryResponse create(CreateProductCategory command) {
        var result = productCategoryServicesFacade.createProductCategory(command);
        return new CreateProductCategoryResponse(result.id, result.name);
    }

}

