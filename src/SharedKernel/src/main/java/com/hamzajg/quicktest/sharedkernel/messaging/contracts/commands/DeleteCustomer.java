package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class DeleteCustomer implements Command {
    public String customerId;

    public DeleteCustomer(String customerId) {
        this.customerId = customerId;
    }
}
