package com.hamzajg.quicktest.customer.domain.events;

import java.util.UUID;

public class CustomerCreated implements Event {
    private final UUID id;
    private final UUID customerId;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerAddress;
    private final String customerEaiml;
    private final String customerMobile;

    public CustomerCreated(UUID customerId, String customerFirstName, String customerLastName, String customerAddress,
                           String customerEaiml, String customerMobile) {
        id = UUID.randomUUID();
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerEaiml = customerEaiml;
        this.customerMobile = customerMobile;
    }
}
