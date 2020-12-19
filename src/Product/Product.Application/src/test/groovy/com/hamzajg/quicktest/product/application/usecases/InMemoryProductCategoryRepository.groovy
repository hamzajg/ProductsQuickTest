package com.hamzajg.quicktest.product.application.usecases

import com.hamzajg.quicktest.product.domain.entities.ProductCategory
import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository

class InMemoryProductCategoryRepository implements ProductCategoryRepository {
    private final List<ProductCategory> productCategoryList = new ArrayList<>()

    @Override
    Collection<ProductCategory> getAll() {
        return productCategoryList
    }

    @Override
    ProductCategory save(ProductCategory productCategory) {
        productCategoryList.add(productCategory)
        return productCategory
    }

    @Override
    ProductCategory getOneById(UUID productCategoryId) {
        return productCategoryList.stream().filter(c -> c.id() == productCategoryId).findFirst().orElse(null)
    }
}
