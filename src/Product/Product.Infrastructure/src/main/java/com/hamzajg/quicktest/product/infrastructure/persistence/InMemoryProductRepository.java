package com.hamzajg.quicktest.product.infrastructure.persistence;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class InMemoryProductRepository implements ProductRepository {
    private static final List<Product> productList = new ArrayList<>();

    @Override
    public Product getOneById(UUID id) {
        return productList.stream().filter(p -> p.id() == id).findFirst().orElse(null);
    }

    @Override
    public Collection<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }
}
