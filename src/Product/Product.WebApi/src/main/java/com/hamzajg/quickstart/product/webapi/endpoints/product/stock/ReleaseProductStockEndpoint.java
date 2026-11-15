package com.hamzajg.quickstart.product.webapi.endpoints.product.stock;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/products")
public class ReleaseProductStockEndpoint {

    @Inject
    ProductServicesFacade productServicesFacade;

    @POST
    @Path("/{productId}/release")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response release(@PathParam("productId") String productId, StockChangeRequest req) {
        if (req == null || req.qty <= 0)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StockErrorResponse("INVALID_QUANTITY")).build();

        try {
            var product = productServicesFacade.releaseStock(productId, req.qty);
            if (product == null)
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new StockErrorResponse("PRODUCT_NOT_FOUND")).build();
            return Response.ok(new StockChangeResponse(product.id, product.availableQty)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StockErrorResponse("INVALID_PRODUCT_ID")).build();
        }
    }
}
