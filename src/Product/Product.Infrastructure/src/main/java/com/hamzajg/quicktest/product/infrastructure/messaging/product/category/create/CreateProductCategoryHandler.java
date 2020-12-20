package com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.application.usecases.CreateProductCategoryUseCase;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductCategoryRepository;
import com.hamzajg.quicktest.product.infrastructure.persistence.InMemoryProductRepository;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateProductCategoryHandler implements CommandHandler {

    public static UnitOfWork unitOfWork = new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository());
    @Inject
    CreateProductCategoryUseCase createProductCategoryUseCase = new CreateProductCategoryUseCase(unitOfWork);
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = createProductCategoryUseCase.execute(((CreateProductCategory) command).name);

        var subs = new CreateProductCategoryEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new CreateProductCategoryResponse(result.id()));
    }
}
