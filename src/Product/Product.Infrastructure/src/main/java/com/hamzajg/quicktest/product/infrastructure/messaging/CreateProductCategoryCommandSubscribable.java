package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreateProductCategoryCommandSubscribable implements Subscribable {
    private Set<Class<?>> supports = new HashSet<>(Collections.singletonList(CreateProductCategory.class));
    private CommandHandler commandHandler;

    public CreateProductCategoryCommandSubscribable(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void handle(Exchange<?> event) {
        commandHandler.handle((Command) event.getData());
    }

    @Override
    public Set<Class<?>> supports() {
        return supports;
    }
}
