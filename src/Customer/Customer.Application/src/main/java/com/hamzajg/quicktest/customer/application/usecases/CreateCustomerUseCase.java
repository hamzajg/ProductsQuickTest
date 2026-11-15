package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;
import com.hamzajg.quicktest.customer.domain.entities.Customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateCustomerUseCase {
    private final UnitOfWork unitOfWork;
@Inject
    public CreateCustomerUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Customer execute(String customerFirstName, String customerLastName, String customerAddress,
                            String customerEmail, String customerMobile) {
        return unitOfWork.customerRepository().save(new Customer(customerFirstName, customerLastName, customerAddress,
                customerEmail, customerMobile));
    }
}
