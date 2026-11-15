package com.hamzajg.quickstart.order.webapi.endpoints.orders.get;

import com.hamzajg.quickstart.order.webapi.endpoints.OrderServicesFacade;
import com.hamzajg.quickstart.order.webapi.endpoints.orders.CreateOrderResponse;
import com.hamzajg.quicktest.order.domain.entities.Order;

import java.util.Collection;
import java.util.stream.Collectors;

public class GetOrdersResponse {
    public Collection<CreateOrderResponse> items;

    public GetOrdersResponse(Collection<Order> orders) {
        this.items = orders.stream()
                .map(CreateOrderResponse::from)
                .collect(Collectors.toList());
    }
}
