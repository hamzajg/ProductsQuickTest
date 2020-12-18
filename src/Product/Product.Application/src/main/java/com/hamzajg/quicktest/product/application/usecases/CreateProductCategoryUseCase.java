package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

public class CreateProductCategoryUseCase {
    private final UnitOfWork unitOfWork;

    public CreateProductCategoryUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public ProductCategory execute(String productCategoryName) {
        return unitOfWork.productCategoryRepository().save(new ProductCategory(productCategoryName));
    }
}
