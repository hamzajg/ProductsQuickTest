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
        return productCategoryList.stream()
                .filter(c -> c.id() == productCategoryId)
                .findFirst()
                .orElse(null)
    }

    @Override
    ProductCategory update(ProductCategory newProductCategory) {
        var exist = getOneById(newProductCategory.id());
        if (exist == null)
            return null
        productCategoryList.remove(exist)
        productCategoryList.add(newProductCategory)
        return newProductCategory
    }

    @Override
    ProductCategory delete(UUID id) {
        var exist = getOneById(id)
        if (exist == null)
            return null
        productCategoryList.remove(exist)
        return exist
    }
}
