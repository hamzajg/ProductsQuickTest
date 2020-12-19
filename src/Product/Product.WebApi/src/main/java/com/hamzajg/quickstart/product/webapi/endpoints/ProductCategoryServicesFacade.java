package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.application.services.BaseProductCategoryServices;
import com.hamzajg.quicktest.product.application.services.ReadProductCategoryServices;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductCategoriesUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetProductCategoryByIdUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.CreateProductCategoryHandler;
import com.hamzajg.quicktest.product.infrastructure.messaging.InMemoryProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductCategoryMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class ProductCategoryServicesFacade {
    //    @Inject
    private ProductCategoryCommandReqResPublisher productCategoryPublisherProvider = new InMemoryProductCategoryCommandReqResPublisher();
    private ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();
    //    @Inject
    private ReadProductCategoryServices productCategoryServices = new BaseProductCategoryServices(new GetAllProductCategoriesUseCase(CreateProductCategoryHandler.unitOfWork), new GetProductCategoryByIdUseCase(CreateProductCategoryHandler.unitOfWork));

    public ProductCategoryDto createProductCategory(CreateProductCategory command) {
        var result = productCategoryPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productCategoryMapper.productCategoryToProductCategoryDto(result);
    }

    public Collection<ProductCategoryDto> getAllProductCategories() {
        return productCategoryMapper.productCategoriesToProductCategoriesDto(productCategoryServices.getAllProductCategories());
    }

    public ProductCategoryDto getOneProductCategoryById(String productCategoryId) {
        var result = productCategoryServices.getOneProductCategoryById(productCategoryId);
        if (result == null)
            return null;
        return productCategoryMapper.productCategoryToProductCategoryDto(result);
    }
}
