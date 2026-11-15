package com.hamzajg.quickstart.order.webapi.endpoints.orders.get;

import com.hamzajg.quickstart.order.webapi.endpoints.OrderServicesFacade;
import com.hamzajg.quickstart.order.webapi.endpoints.orders.CreateOrderResponse;
import com.hamzajg.quickstart.order.webapi.endpoints.orders.OrderErrorResponse;
import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/orders")
public class GetOrdersEndpoint {

    @Inject
    OrderServicesFacade orderServicesFacade;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public GetOrdersResponse getAll() {
        return new GetOrdersResponse(orderServicesFacade.listOrders());
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("orderId") String orderId) {
        try {
            return Response.ok(CreateOrderResponse.from(orderServicesFacade.getOrder(orderId))).build();
        } catch (CheckoutException e) {
            if ("ORDER_NOT_FOUND".equals(e.code()))
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new OrderErrorResponse(e.code())).build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new OrderErrorResponse(e.code())).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new OrderErrorResponse("ORDER_NOT_FOUND")).build();
        }
    }
}
