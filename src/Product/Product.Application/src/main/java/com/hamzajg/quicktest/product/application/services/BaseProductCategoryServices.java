package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.application.usecases.GetAllProductCategoriesUseCase;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductCategoryMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class BaseProductCategoryServices implements ReadProductCategoryServices {

    private GetAllProductCategoriesUseCase getAllProductCategoriesUseCase;
    private ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();

    @Inject
    public BaseProductCategoryServices(GetAllProductCategoriesUseCase getAllProductCategoriesUseCase) {
        this.getAllProductCategoriesUseCase = getAllProductCategoriesUseCase;
    }

    @Override
    public Collection<ProductCategoryDto> getAllProductCategories() {
        return productCategoryMapper.productCategoriesToProductCategoriesDto(getAllProductCategoriesUseCase.execute());
    }
}
