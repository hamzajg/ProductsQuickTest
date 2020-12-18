package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    private UnitOfWork unitOfWork;

    @Inject
    public CreateProductUseCase() {

    }

    public CreateProductUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product Execute(String name, String categoryName, float unitPrice, float discount, Integer availableQty) {
        var product = new Product(name, new ProductCategory(categoryName), unitPrice, discount, availableQty);
        return unitOfWork.productRepository().save(product);
    }
}
