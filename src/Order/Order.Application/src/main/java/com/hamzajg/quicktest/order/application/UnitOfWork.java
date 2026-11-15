package com.hamzajg.quicktest.order.application;

import com.hamzajg.quicktest.order.domain.entities.OrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UnitOfWork {
    private final OrderRepository orderRepository;

    @Inject
    public UnitOfWork(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderRepository orderRepository() {
        return orderRepository;
    }
}
