package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.messaging.ProductCategoryCommandReqResPublisher;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.CreateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
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
    UnitOfWork unitOfWork = new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository());
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public ProductCategory publishAndWait(Command command) {
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
        return unitOfWork
                .productCategoryRepository()
                .getOneById(((CreateProductCategoryResponse) event.getResponse())
                        .getProductCategoryId());
    }

}
