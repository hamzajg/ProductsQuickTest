package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.messaging.ProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.application.services.BaseProductService;
import com.hamzajg.quicktest.product.application.services.ReadProductService;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsByCategoryUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.InMemoryProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class ProductServicesFacade {
    private ReadProductService baseProductService = new BaseProductService(new GetAllProductsByCategoryUseCase(new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository())));
    private ProductCommandReqResPublisher productPublisherProvider = new InMemoryProductCommandReqResPublisher();
    private ProductMapper productMapper = new ProductMapper();

    public ProductDto createProduct(CreateProduct command) {
        var result = productPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productMapper.productToProductDto(result);
    }

    public Collection<ProductDto> getAllProductsByCategoryId(String productCategoryId) {
        var result = baseProductService.getAllProductsByCategoryId(UUID.fromString(productCategoryId));
        if (result == null)
            return null;
        return productMapper.productsToProductsDto(result);
    }
}
