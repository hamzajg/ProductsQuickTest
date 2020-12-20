package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.DeleteProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InMemoryProductCategoryCommandReqResPublisher implements ProductCategoryCommandReqResPublisher {

    @Inject
    CreateProductCategoryHandler createProductCategoryHandler = new CreateProductCategoryHandler();
    @Inject
    UpdateProductCategoryHandler updateProductCategoryHandler = new UpdateProductCategoryHandler();
    @Inject
    DeleteProductCategoryHandler deleteProductCategoryHandler = new DeleteProductCategoryHandler();

    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public ProductCategory publishAndWait(Command command) {
        if (command instanceof CreateProductCategory)
            return createProductCategory(command);
        else if (command instanceof UpdateProductCategory)
            return updateProductCategory(command);
        else
            return deleteProductCategory(command);
    }

    private ProductCategory createProductCategory(Command command) {
        var subs = new CreateProductCategoryCommandSubscribable(createProductCategoryHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof CreateProductCategoryEventSubscribable)
                .map(sub -> (CreateProductCategoryEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateProductCategoryHandler.unitOfWork
                .productCategoryRepository()
                .getOneById(((CreateProductCategoryResponse) event.getResponse())
                        .getProductCategoryId());
    }

    private ProductCategory updateProductCategory(Command command) {
        var subs = new UpdateProductCategoryCommandSubscribable(updateProductCategoryHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof UpdateProductCategoryEventSubscribable)
                .map(sub -> (UpdateProductCategoryEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateProductCategoryHandler.unitOfWork
                .productCategoryRepository()
                .getOneById(((UpdateProductCategoryResponse) event.getResponse())
                        .getProductCategoryId());
    }
    private ProductCategory deleteProductCategory(Command command) {
        var subs = new DeleteProductCategoryCommandSubscribable(deleteProductCategoryHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof DeleteProductCategoryEventSubscribable)
                .map(sub -> (DeleteProductCategoryEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return ((DeleteProductCategoryResponse) event.getResponse()).getProductCategory();
    }

}
