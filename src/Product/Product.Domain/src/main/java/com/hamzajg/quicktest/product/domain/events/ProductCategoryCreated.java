package com.hamzajg.quicktest.product.domain.events;

import java.util.UUID;

public class ProductCategoryCreated implements Event {

    private final UUID id;
    private final UUID productCategoryId;
    private final String productCategoryName;

    public ProductCategoryCreated(UUID productCategoryId, String productCategoryName) {
        this.id = UUID.randomUUID();
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
    }
}
