package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class GetAllProductsByCategoryUseCase {

    private UnitOfWork unitOfWork;

    @Inject
    public GetAllProductsByCategoryUseCase() {

    }

    public GetAllProductsByCategoryUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Collection<Product> execute(UUID productCategoryId) {
        return unitOfWork.productRepository().getAllByCategoryId(productCategoryId);
    }
}
