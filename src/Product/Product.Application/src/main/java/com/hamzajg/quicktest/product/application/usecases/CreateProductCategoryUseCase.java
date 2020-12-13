package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

public class CreateProductCategoryUseCase {
    private final UnitOfWork unitOfWork;

    public CreateProductCategoryUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public void Execute(String productCategoryName) {
        unitOfWork.productCategoryRepository().save(new ProductCategory(productCategoryName));
    }
}
