package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.CustomerCreated;

import java.util.UUID;

public class Customer extends Entity {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String mobile;

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
}
