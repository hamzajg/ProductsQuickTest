package com.hamzajg.quicktest.product.application.usecases

import com.hamzajg.quicktest.product.domain.entities.Product
import com.hamzajg.quicktest.product.domain.entities.ProductRepository

import java.util.stream.Collectors

class InMemoryProductRepository implements ProductRepository {
    private final List<Product> productList = new ArrayList<>()

    @Override
    Product getOneById(UUID id) {
        return productList.stream().filter(p -> p.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    Collection<Product> getAll() {
        return productList
    }

    @Override
    Product save(Product product) {
        productList.add(product)
        return product
    }

    @Override
    Collection<Product> getAllByCategoryId(UUID productCategoryId) {
        return productList.stream().filter(p -> p.category().id().equals(productCategoryId)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    Product update(Product newProduct) {
        var exist = productList.stream().filter(c -> c.id() == newProduct.id()).findFirst().orElse(null);
        if (exist == null)
            return null;
        productList.remove(exist);
        productList.add(newProduct);
        return newProduct;
    }
}
