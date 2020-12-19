package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class GetAllProductsUseCase {

    private UnitOfWork unitOfWork;

    @Inject
    public GetAllProductsUseCase() {

    }

    public GetAllProductsUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Collection<Product> execute() {
        return unitOfWork.productRepository().getAll();
    }
}
