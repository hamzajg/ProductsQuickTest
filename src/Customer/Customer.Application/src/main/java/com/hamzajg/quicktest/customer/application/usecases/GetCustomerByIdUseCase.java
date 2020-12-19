package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;
import com.hamzajg.quicktest.customer.domain.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class GetCustomerByIdUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public GetCustomerByIdUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Customer execute(UUID customerId) {
        return unitOfWork.customerRepository().getOneById(customerId);
    }
}
