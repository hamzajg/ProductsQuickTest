package com.hamzajg.quicktest.order.application.usecases;

import com.hamzajg.quicktest.order.application.UnitOfWork;
import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.domain.entities.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class GetOrderByIdUseCase {
    private final UnitOfWork unitOfWork;

    @Inject
    public GetOrderByIdUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Order execute(UUID orderId) {
        var order = unitOfWork.orderRepository().getOneById(orderId);
        if (order == null)
            throw new CheckoutException("ORDER_NOT_FOUND");
        return order;
    }
}
