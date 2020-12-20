package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;
import com.hamzajg.quicktest.customer.domain.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class DeleteCustomerUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public DeleteCustomerUseCase(UnitOfWork unitOfWork) {

        this.unitOfWork = unitOfWork;
    }

    public Customer execute(UUID id) {
        return unitOfWork.customerRepository().delete(id);
    }
}
