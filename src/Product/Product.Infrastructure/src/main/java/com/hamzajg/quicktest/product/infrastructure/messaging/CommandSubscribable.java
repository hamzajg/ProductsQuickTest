package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CommandSubscribable implements Subscribable {
    private Set<Class<?>> supports = new HashSet<>(Collections.singletonList(CreateProduct.class));
    private CreateProductHandler createProductHandler;

    public CommandSubscribable(CreateProductHandler createProductHandler) {
        this.createProductHandler = createProductHandler;
    }

    @Override
    public void handle(Exchange<?> event) {
        createProductHandler.handle((Command) event.getData());
    }

    @Override
    public Set<Class<?>> supports() {
        return supports;
    }
}

