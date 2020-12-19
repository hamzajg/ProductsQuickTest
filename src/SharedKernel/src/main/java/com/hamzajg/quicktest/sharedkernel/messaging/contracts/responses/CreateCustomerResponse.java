package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class CreateCustomerResponse implements Response {
    private UUID customerId;

    public CreateCustomerResponse(UUID id) {
        this.customerId = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }
}
