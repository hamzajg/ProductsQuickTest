package com.hamzajg.quicktest.order.application.usecases;

import com.hamzajg.quicktest.order.application.UnitOfWork;
import com.hamzajg.quicktest.order.domain.entities.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class GetAllOrdersUseCase {
    private final UnitOfWork unitOfWork;

    @Inject
    public GetAllOrdersUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Collection<Order> execute() {
        return unitOfWork.orderRepository().getAll();
    }
}
