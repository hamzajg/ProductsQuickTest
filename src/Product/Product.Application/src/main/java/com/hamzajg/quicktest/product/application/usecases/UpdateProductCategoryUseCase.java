package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import javax.inject.Inject;
import java.util.UUID;

public class UpdateProductCategoryUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public UpdateProductCategoryUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public ProductCategory execute(UUID productCategoryId, String name) {
        var result = unitOfWork.productCategoryRepository().getOneById(productCategoryId);
        if(result == null)
            return null;
        if(!result.name().equals(name))
            result.changeName(name);

        return unitOfWork.productCategoryRepository().update(result);
    }
}
