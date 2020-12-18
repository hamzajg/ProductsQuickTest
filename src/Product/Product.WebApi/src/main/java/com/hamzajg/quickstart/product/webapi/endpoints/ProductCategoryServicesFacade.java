package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.application.services.BaseProductCategoryServices;
import com.hamzajg.quicktest.product.application.services.ReadProductCategoryServices;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductCategoriesUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.InMemoryProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductCategoryMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class ProductCategoryServicesFacade {
//    @Inject
    private ProductCategoryCommandReqResPublisher productCategoryPublisherProvider = new InMemoryProductCategoryCommandReqResPublisher();
    private ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();
//    @Inject
    private ReadProductCategoryServices productCategoryServices = new BaseProductCategoryServices(new GetAllProductCategoriesUseCase(new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository())));
    public ProductCategoryDto createProductCategory(CreateProductCategory command) {
        var result = productCategoryPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productCategoryMapper.productCategoryToProductCategoryDto(result);
    }

    public Collection<ProductCategoryDto> getAllProductCategories() {
        return productCategoryServices.getAllProductCategories();
    }
}
