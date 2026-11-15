package com.hamzajg.quicktest.order.infrastructure.persistence;

import com.hamzajg.quicktest.order.domain.entities.Order;
import com.hamzajg.quicktest.order.domain.entities.OrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Collection<Order> getAll() {
        return orderList;
    }

    @Override
    public Order getOneById(UUID id) {
        return orderList.stream().filter(o -> o.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Order save(Order order) {
        orderList.add(order);
        return order;
    }
}
