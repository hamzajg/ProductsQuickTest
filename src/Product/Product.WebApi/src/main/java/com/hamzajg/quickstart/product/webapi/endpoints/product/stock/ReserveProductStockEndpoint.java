package com.hamzajg.quickstart.product.webapi.endpoints.product.stock;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;
import com.hamzajg.quicktest.product.domain.exceptions.InsufficientStockException;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/products")
public class ReserveProductStockEndpoint {

    @Inject
    ProductServicesFacade productServicesFacade;

    @POST
    @Path("/{productId}/reserve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reserve(@PathParam("productId") String productId, StockChangeRequest req) {
        if (req == null || req.qty <= 0)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StockErrorResponse("INVALID_QUANTITY")).build();

        try {
            var product = productServicesFacade.reserveStock(productId, req.qty);
            if (product == null)
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new StockErrorResponse("PRODUCT_NOT_FOUND")).build();
            return Response.ok(new StockChangeResponse(product.id, product.availableQty)).build();
        } catch (InsufficientStockException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new StockErrorResponse("PRODUCT_OUT_OF_STOCK")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StockErrorResponse("INVALID_PRODUCT_ID")).build();
        }
    }
}
