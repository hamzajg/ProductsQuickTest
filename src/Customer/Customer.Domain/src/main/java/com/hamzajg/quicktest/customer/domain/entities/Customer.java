package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.CustomerCreated;

import java.util.UUID;

public class Customer extends Entity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobile;

    public Customer(String firstName, String lastName, String address, String email, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;

        addEvent(new CustomerCreated(id, firstName, lastName, address, email, mobile));
    }

    public UUID id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String address() {
        return address;
    }

    public String email() {
        return email;
    }

    public String mobile() {
        return mobile;
    }

    public void changeFirstName(String newFirstName) {
        firstName = newFirstName;
        addEvent(new CustomerFirstNameChanged(id, newFirstName));
    }

    public void changeLastName(String newLastName) {
        lastName = newLastName;
        addEvent(new CustomerLastNameChanged(id, newLastName));
    }

    public void changeAddress(String newAddress) {
        address = newAddress;
        addEvent(new CustomerAddressChanged(id, newAddress));
    }

    public void changeEmail(String newEmail) {
        email = newEmail;
        addEvent(new CustomerEmailChanged(id, newEmail));
    }

    public void changeMobile(String newMobile) {
        mobile = newMobile;
        addEvent(new CustomerMobileChanged(id, newMobile));
    }
}
