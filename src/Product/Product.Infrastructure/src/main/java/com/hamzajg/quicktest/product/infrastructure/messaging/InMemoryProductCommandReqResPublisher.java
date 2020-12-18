package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.messaging.ProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.CreateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InMemoryProductCommandReqResPublisher implements ProductCommandReqResPublisher {

    @Inject
    CreateProductHandler createProductHandler = new CreateProductHandler();
    @Inject
    UnitOfWork unitOfWork = new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository());
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public Product publishAndWait(Command command) {
        var subs = new CommandSubscribable(createProductHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof EventSubscribable)
                .map(sub -> (EventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return unitOfWork.productRepository().getOneById(((CreateProductResponse) event.getResponse()).getProductId());
    }

}
