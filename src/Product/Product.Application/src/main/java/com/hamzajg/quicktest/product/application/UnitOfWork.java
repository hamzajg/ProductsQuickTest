package com.hamzajg.quicktest.product.application;

import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository;
import com.hamzajg.quicktest.product.domain.entities.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UnitOfWork {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Inject
    public UnitOfWork(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    public ProductCategoryRepository productCategoryRepository() {
        return productCategoryRepository;
    }

    public ProductRepository productRepository() {
        return productRepository;
    }
}
