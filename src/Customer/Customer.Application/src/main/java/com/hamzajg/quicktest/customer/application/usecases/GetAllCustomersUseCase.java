package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;
import com.hamzajg.quicktest.customer.domain.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class GetAllCustomersUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public GetAllCustomersUseCase(UnitOfWork unitOfWork) {

        this.unitOfWork = unitOfWork;
    }

    public Collection<Customer> execute() {
        return unitOfWork.customerRepository().getAll();
    }
}
