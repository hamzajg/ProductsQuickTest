package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetCustomersByIdUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public GetCustomersByIdUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }
}
