package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class GetAllProductCategoriesUseCase {

    private UnitOfWork unitOfWork;

    @Inject
    public GetAllProductCategoriesUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Collection<ProductCategory> execute() {
        return unitOfWork.productCategoryRepository().getAll();
    }
}
