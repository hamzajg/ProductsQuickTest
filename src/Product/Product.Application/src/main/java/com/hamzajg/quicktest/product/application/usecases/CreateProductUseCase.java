package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

public class CreateProductUseCase {
    private final UnitOfWork unitOfWork;

    public CreateProductUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public void Execute(String name, String categoryName, float unitPrice, float discount, Integer availableQty) {
        unitOfWork.productRepository().save(new Product(name, new ProductCategory(categoryName), unitPrice, discount, availableQty));
    }
}
