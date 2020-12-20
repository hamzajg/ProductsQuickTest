package com.hamzajg.quicktest.product.infrastructure.messaging;


import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.DeleteProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DeleteProductCategoryEventSubscribable implements Subscribable {
    private Set<Class<?>> supports = new HashSet<>(Collections.singletonList(DeleteProductCategoryResponse.class));
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
