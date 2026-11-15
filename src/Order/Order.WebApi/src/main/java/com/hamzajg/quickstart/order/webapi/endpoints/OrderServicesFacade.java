package com.hamzajg.quickstart.order.webapi.endpoints;

import com.hamzajg.quicktest.order.application.usecases.PlaceOrderItem;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderUseCase;
import com.hamzajg.quicktest.order.domain.entities.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderServicesFacade {

    @Inject
    PlaceOrderUseCase placeOrderUseCase;

    public Order placeOrder(String customerId, List<PlaceOrderItem> items) {
        return placeOrderUseCase.execute(UUID.fromString(customerId), items);
    }
}
