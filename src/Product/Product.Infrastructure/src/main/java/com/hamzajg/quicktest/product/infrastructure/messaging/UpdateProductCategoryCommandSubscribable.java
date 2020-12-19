package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UpdateProductCategoryCommandSubscribable implements Subscribable {
    private Set<Class<?>> supports = new HashSet<>(Collections.singletonList(UpdateProductCategory.class));
    private CommandHandler commandHandler;

    public UpdateProductCategoryCommandSubscribable(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void handle(Exchange<?> event) {
        commandHandler.handle((Command) event.getData());
        var bus = BusFactory.createSingletonSyncBus();
        bus.getSubscribers().remove(this);
    }

    @Override
    public Set<Class<?>> supports() {
        return supports;
    }
}
