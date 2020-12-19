package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.services.BaseProductService;
import com.hamzajg.quicktest.product.application.services.WriteProductService;
import com.hamzajg.quicktest.product.application.usecases.CreateProductUseCase;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateProductHandler implements CommandHandler {

    @Inject
    WriteProductService writeProductService = new BaseProductService(new CreateProductUseCase(CreateProductCategoryHandler.unitOfWork));
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = writeProductService.createProduct((CreateProduct) command);

        var subs = new CreateProductEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new CreateProductResponse(result.id()));
    }
}
