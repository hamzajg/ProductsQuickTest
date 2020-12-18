package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.CreateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EventSubscribable implements Subscribable {
    private Set<Class<?>> supports = new HashSet<>(Collections.singletonList(CreateProductResponse.class));
    private Response response;

    @Override
    public void handle(Exchange<?> event) {
        response = (Response) event.getData();
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public Set<Class<?>> supports() {
        return supports;
    }
}
