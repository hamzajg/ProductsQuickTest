package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;


@ApplicationScoped
public class DeleteProductCategoryUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public DeleteProductCategoryUseCase(UnitOfWork unitOfWork) {

        this.unitOfWork = unitOfWork;
    }

    public ProductCategory execute(UUID id) {
        return unitOfWork.productCategoryRepository().delete(id);
    }
}
