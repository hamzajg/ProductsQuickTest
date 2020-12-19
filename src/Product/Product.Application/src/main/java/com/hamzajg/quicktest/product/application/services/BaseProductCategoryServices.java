package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.application.usecases.GetAllProductCategoriesUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetProductCategoryByIdUseCase;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class BaseProductCategoryServices implements ReadProductCategoryServices {

    private GetAllProductCategoriesUseCase getAllProductCategoriesUseCase;
    private GetProductCategoryByIdUseCase getProductCategoryByIdUseCase;

    @Inject
    public BaseProductCategoryServices(GetAllProductCategoriesUseCase getAllProductCategoriesUseCase,
                                       GetProductCategoryByIdUseCase getProductCategoryByIdUseCase) {
        this.getAllProductCategoriesUseCase = getAllProductCategoriesUseCase;
        this.getProductCategoryByIdUseCase = getProductCategoryByIdUseCase;
    }

    @Override
    public Collection<ProductCategory> getAllProductCategories() {
        return getAllProductCategoriesUseCase.execute();
    }

    @Override
    public ProductCategory getOneProductCategoryById(String productCategoryId) {
        return getProductCategoryByIdUseCase.execute(UUID.fromString(productCategoryId));
    }
}
