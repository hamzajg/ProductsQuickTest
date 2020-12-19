package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class UpdateCustomer implements Command {
    public String customerId;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String mobile;

    public UpdateCustomer(String customerId, String firstName, String lastName, String address, String email, String mobile) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }
}
