package com.hamzajg.quicktest.product.application;

import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository;
import com.hamzajg.quicktest.product.domain.entities.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UnitOfWork {
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    @Inject
    public UnitOfWork() {

    }

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
