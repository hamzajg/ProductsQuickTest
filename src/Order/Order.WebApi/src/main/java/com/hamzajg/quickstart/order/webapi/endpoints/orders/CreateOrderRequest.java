package com.hamzajg.quickstart.order.webapi.endpoints.orders;

import java.util.List;

public class CreateOrderRequest {
    public String customerId;
    public List<CreateOrderItemRequest> items;
}
