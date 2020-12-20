package com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

public class DeleteProductCategoryResponse implements Response {
    private ProductCategory productCategory;

    public DeleteProductCategoryResponse(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
