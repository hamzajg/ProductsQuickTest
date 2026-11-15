package com.hamzajg.quicktest.order.application.usecases;

import com.hamzajg.quicktest.order.application.UnitOfWork;
import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.application.ports.CustomerGateway;
import com.hamzajg.quicktest.order.application.ports.ProductGateway;
import com.hamzajg.quicktest.order.domain.entities.Order;
import com.hamzajg.quicktest.order.domain.entities.OrderItem;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Runs a checkout across two other services. There is no distributed
 * transaction here: the stock each line holds is released again if a later line
 * cannot be held, so a failed checkout leaves no stock stranded and no order
 * stored.
 */
@ApplicationScoped
public class PlaceOrderUseCase {
    private final UnitOfWork unitOfWork;
    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;

    @Inject
    public PlaceOrderUseCase(UnitOfWork unitOfWork, CustomerGateway customerGateway, ProductGateway productGateway) {
        this.unitOfWork = unitOfWork;
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    public Order execute(UUID customerId, List<PlaceOrderItem> items) {
        if (customerId == null || items == null || items.isEmpty())
            throw new CheckoutException("INVALID_ORDER");

        if (!customerGateway.exists(customerId))
            throw new CheckoutException("CUSTOMER_NOT_FOUND");

        var held = new ArrayList<OrderItem>();
        try {
            for (var item : items) {
                if (item.qty() <= 0)
                    throw new CheckoutException("INVALID_QUANTITY");
                var snapshot = productGateway.reserve(item.productId(), item.qty());
                held.add(new OrderItem(item.productId(), item.qty(), snapshot.unitPrice()));
            }
        } catch (CheckoutException e) {
            releaseAll(held);
            throw e;
        }

        return unitOfWork.orderRepository().save(new Order(customerId, held));
    }

    private void releaseAll(List<OrderItem> held) {
        for (var item : held)
            productGateway.release(item.productId(), item.qty());
    }
}
