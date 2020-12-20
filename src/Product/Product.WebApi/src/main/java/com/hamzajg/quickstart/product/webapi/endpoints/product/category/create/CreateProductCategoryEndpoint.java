package com.hamzajg.quickstart.product.webapi.endpoints.product.category.create;

import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

