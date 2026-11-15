package com.hamzajg.quicktest.order.domain.entities;

import com.hamzajg.quicktest.order.domain.events.OrderPlaced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * An order a customer placed. It is created only once every line has been
 * priced and its stock has been held, so an {@code Order} always represents a
 * complete, committed purchase.
 */
public class Order extends Entity {
    public static final String STATUS_PLACED = "PLACED";

    private final UUID customerId;
    private final Collection<OrderItem> items;
    private final float total;

    public Order(UUID customerId, Collection<OrderItem> items) {
        super();
        if (items == null || items.isEmpty())
            throw new IllegalArgumentException("An order needs at least one item");
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
        this.total = computeTotal(this.items);

        addEvent(new OrderPlaced(id, customerId, total));
    }

    private static float computeTotal(Collection<OrderItem> items) {
        var total = 0f;
        for (var item : items)
            total += item.lineTotal();
        return total;
    }

    public UUID id() {
        return id;
    }

    public UUID customerId() {
        return customerId;
    }

    public String status() {
        return STATUS_PLACED;
    }

    public float total() {
        return total;
    }

    public List<OrderItem> items() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }
}
