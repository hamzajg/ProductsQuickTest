package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class UpdateProductResponse implements Response {
    private UUID productId;

    public UpdateProductResponse(UUID id) {
        this.productId = id;
    }

    public UUID getProductId() {
        return productId;
    }
}
