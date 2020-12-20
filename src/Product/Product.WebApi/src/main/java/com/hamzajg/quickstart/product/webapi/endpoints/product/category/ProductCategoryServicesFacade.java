package com.hamzajg.quickstart.product.webapi.endpoints.product.category;

import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.application.services.BaseProductCategoryServices;
import com.hamzajg.quicktest.product.application.services.ReadProductCategoryServices;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductCategoriesUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetProductCategoryByIdUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.InMemoryProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create.CreateProductCategoryHandler;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductCategoryMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProductCategory;

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

    public ProductCategoryDto updateProductCategory(UpdateProductCategory command) {
        var result = productCategoryPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productCategoryMapper.productCategoryToProductCategoryDto(result);
    }

    public String deleteProductCategory(String productCategoryId) {
        var result = productCategoryPublisherProvider.publishAndWait(new DeleteProductCategory(productCategoryId));
        if (result == null)
            return null;
        return result.id().toString();
    }
}
