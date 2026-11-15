package com.hamzajg.quicktest.order.domain.entities;

import java.util.Collection;
import java.util.UUID;

public interface OrderRepository {
    Collection<Order> getAll();

    Order getOneById(UUID id);

    Order save(Order order);
}
