package com.hamzajg.quickstart.order.webapi.endpoints.orders;

import com.hamzajg.quicktest.order.domain.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderResponse {
    public String orderId;
    public String customerId;
    public String status;
    public float total;
    public List<CreateOrderItemResponse> items;

    public CreateOrderResponse(String orderId, String customerId, String status, float total,
                               List<CreateOrderItemResponse> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.total = total;
        this.items = items;
    }

    public static CreateOrderResponse from(Order order) {
        var items = order.items().stream()
                .map(item -> new CreateOrderItemResponse(item.productId().toString(), item.qty(), item.unitPrice()))
                .collect(Collectors.toList());
        return new CreateOrderResponse(order.id().toString(), order.customerId().toString(), order.status(),
                order.total(), items);
    }
}
