package com.hamzajg.quickstart.product.webapi.endpoints.product.webapi.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
public class CreateProductEndpoint {
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateProductResponse create(CreateProductRequest req) {
        return new CreateProductResponse();
    }

    public class CreateProductRequest {
    }

    public class CreateProductResponse {
    }
}
