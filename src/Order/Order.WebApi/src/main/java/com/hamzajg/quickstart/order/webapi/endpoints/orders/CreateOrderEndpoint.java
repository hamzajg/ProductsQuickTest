package com.hamzajg.quickstart.order.webapi.endpoints.orders;

import com.hamzajg.quickstart.order.webapi.endpoints.OrderServicesFacade;
import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderItem;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/api/v1/orders")
public class CreateOrderEndpoint {

    @Inject
    OrderServicesFacade orderServicesFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateOrderRequest request) {
        if (request == null || request.customerId == null || request.items == null || request.items.isEmpty())
            return badRequest("INVALID_ORDER");

        try {
            var order = orderServicesFacade.placeOrder(request.customerId, toItems(request));
            return Response.ok(CreateOrderResponse.from(order)).build();
        } catch (CheckoutException e) {
            return Response.status(statusFor(e.code()))
                    .entity(new OrderErrorResponse(e.code())).build();
        } catch (IllegalArgumentException e) {
            return badRequest("INVALID_ORDER");
        }
    }

    private List<PlaceOrderItem> toItems(CreateOrderRequest request) {
        var items = new ArrayList<PlaceOrderItem>();
        for (var item : request.items) {
            if (item == null || item.productId == null)
                throw new IllegalArgumentException("An order item needs a productId");
            items.add(new PlaceOrderItem(UUID.fromString(item.productId), item.qty));
        }
        return items;
    }

    private static Response.Status statusFor(String code) {
        switch (code) {
            case "CUSTOMER_NOT_FOUND":
            case "PRODUCT_NOT_FOUND":
                return Response.Status.NOT_FOUND;
            case "PRODUCT_OUT_OF_STOCK":
                return Response.Status.CONFLICT;
            default:
                return Response.Status.BAD_REQUEST;
        }
    }

    private static Response badRequest(String code) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new OrderErrorResponse(code)).build();
    }
}
