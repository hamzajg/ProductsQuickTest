package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.usecases.CreateProductUseCase;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.CreateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.events.Event;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateProductHandler implements CommandHandler {

    @Inject
    CreateProductUseCase createProductUseCase = new CreateProductUseCase(
            new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository()));
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result =  createProductUseCase.Execute(((CreateProduct) command).name, ((CreateProduct) command).categoryName,
                ((CreateProduct) command).unitPrice, ((CreateProduct) command).discount, ((CreateProduct) command).availableQty);

        var subs = new EventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new CreateProductResponse(result.id()));
    }
}
