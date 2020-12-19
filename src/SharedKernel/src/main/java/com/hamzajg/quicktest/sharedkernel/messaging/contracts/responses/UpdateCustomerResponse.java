package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class UpdateCustomerResponse implements Response {
    private UUID customerId;

    public UpdateCustomerResponse(UUID id) {
        this.customerId = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }
}
