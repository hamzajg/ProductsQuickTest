package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class UpdateProductCategoryResponse implements Response {
    private UUID productCategoryId;

    public UpdateProductCategoryResponse(UUID id) {
        this.productCategoryId = id;
    }

    public UUID getProductCategoryId() {
        return productCategoryId;
    }
}
