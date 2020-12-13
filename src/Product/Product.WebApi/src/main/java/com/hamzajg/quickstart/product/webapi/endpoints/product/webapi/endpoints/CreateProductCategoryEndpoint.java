package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/product-categories")
public class CreateProductCategoryEndpoint {
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateProductCategoryResponse create(CreateProductCategoryRequest req) {
        return new CreateProductCategoryResponse();
    }

    public class CreateProductCategoryRequest {
    }

    public class CreateProductCategoryResponse {
    }
}
