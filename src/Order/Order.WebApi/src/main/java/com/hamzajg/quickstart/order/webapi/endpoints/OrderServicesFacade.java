package com.hamzajg.quickstart.order.webapi.endpoints;

import com.hamzajg.quicktest.order.application.usecases.GetAllOrdersUseCase;
import com.hamzajg.quicktest.order.application.usecases.GetOrderByIdUseCase;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderItem;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderUseCase;
import com.hamzajg.quicktest.order.domain.entities.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderServicesFacade {

    @Inject
    PlaceOrderUseCase placeOrderUseCase;
    @Inject
    GetAllOrdersUseCase getAllOrdersUseCase;
    @Inject
    GetOrderByIdUseCase getOrderByIdUseCase;

    public Order placeOrder(String customerId, List<PlaceOrderItem> items) {
        return placeOrderUseCase.execute(UUID.fromString(customerId), items);
    }

    public Collection<Order> listOrders() {
        return getAllOrdersUseCase.execute();
    }

    public Order getOrder(String orderId) {
        return getOrderByIdUseCase.execute(UUID.fromString(orderId));
    }
}
