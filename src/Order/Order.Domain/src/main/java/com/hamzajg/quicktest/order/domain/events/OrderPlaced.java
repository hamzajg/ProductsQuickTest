package com.hamzajg.quicktest.order.domain.events;

import java.util.UUID;

public class OrderPlaced implements Event {
    private final UUID id;
    private final UUID orderId;
    private final UUID customerId;
    private final float total;

    public OrderPlaced(UUID orderId, UUID customerId, float total) {
        id = UUID.randomUUID();
        this.orderId = orderId;
        this.customerId = customerId;
        this.total = total;
    }
}
