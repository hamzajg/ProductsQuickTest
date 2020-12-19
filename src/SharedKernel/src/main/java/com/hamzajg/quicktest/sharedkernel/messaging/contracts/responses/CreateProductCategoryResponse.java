package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import java.util.UUID;

public class CreateProductCategoryResponse implements Response {
    private UUID productCategoryId;

    public CreateProductCategoryResponse(UUID id) {
        this.productCategoryId = id;
    }

    public UUID getProductCategoryId() {
        return productCategoryId;
    }
}
