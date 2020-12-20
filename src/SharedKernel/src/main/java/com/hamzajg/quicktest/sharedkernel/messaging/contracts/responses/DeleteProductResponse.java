package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import com.hamzajg.quicktest.product.domain.entities.Product;

public class DeleteProductResponse implements Response {
    private Product product;

    public DeleteProductResponse(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
