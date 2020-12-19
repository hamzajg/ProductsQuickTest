package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import javax.inject.Inject;
import java.util.UUID;

public class GetProductCategoryByIdUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public GetProductCategoryByIdUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public ProductCategory execute(UUID productCategoryId) {
        return unitOfWork.productCategoryRepository().getOneById(productCategoryId);
    }
}
