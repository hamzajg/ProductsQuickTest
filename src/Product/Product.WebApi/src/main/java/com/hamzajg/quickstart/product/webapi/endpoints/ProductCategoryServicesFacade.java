package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.messaging.InMemoryProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductCategoryMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProductCategoryServicesFacade {
//    @Inject
    ProductCategoryCommandReqResPublisher productCategoryPublisherProvider = new InMemoryProductCategoryCommandReqResPublisher();
    ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();

    public ProductCategoryDto createProductCategory(CreateProductCategory command) {
        var result = productCategoryPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productCategoryMapper.productCategoryToProductCategoryDto(result);
    }
}
