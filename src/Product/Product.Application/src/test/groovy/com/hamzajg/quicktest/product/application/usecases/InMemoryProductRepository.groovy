package com.hamzajg.quicktest.product.application.usecases

import com.hamzajg.quicktest.product.domain.entities.Product
import com.hamzajg.quicktest.product.domain.entities.ProductRepository

class InMemoryProductRepository implements ProductRepository {
    private final List<Product> productList = new ArrayList<>()

    @Override
    Product getOneById(UUID id) {
        return null
    }

    @Override
    Collection<Product> getAll() {
        return productList
    }

    @Override
    void save(Product product) {
        productList.add(product)
    }
}
