package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class CreateProductResponse implements Response {
    private UUID productId;

    public CreateProductResponse(UUID id) {
        this.productId = id;
    }

    public UUID getProductId() {
        return productId;
    }
}
