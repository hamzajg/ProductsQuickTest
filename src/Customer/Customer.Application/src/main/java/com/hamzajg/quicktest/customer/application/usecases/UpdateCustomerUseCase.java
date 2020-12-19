package com.hamzajg.quicktest.customer.application.usecases;

import com.hamzajg.quicktest.customer.application.UnitOfWork;
import com.hamzajg.quicktest.customer.domain.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;
@ApplicationScoped
public class UpdateCustomerUseCase {
    private final UnitOfWork unitOfWork;
@Inject
    public UpdateCustomerUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Customer execute(UUID customerId, String customerFirstName, String customerLastName, String customerAddress,
                            String customerEmail, String customerMobile) {
        var exist = unitOfWork.customerRepository().getOneById(customerId);
        if (exist == null)
            return null;
        if (!exist.firstName().equals(customerFirstName))
            exist.changeFirstName(customerFirstName);
        if (!exist.lastName().equals(customerLastName))
            exist.changeLastName(customerLastName);
        if (!exist.address().equals(customerAddress))
            exist.changeAddress(customerAddress);
        if (!exist.email().equals(customerEmail))
            exist.changeEmail(customerEmail);
        if (!exist.mobile().equals(customerMobile))
            exist.changeMobile(customerMobile);
        return unitOfWork.customerRepository().update(exist);
    }
}
