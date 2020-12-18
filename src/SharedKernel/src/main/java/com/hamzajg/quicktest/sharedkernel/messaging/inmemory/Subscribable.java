package com.hamzajg.quicktest.sharedkernel.messaging.inmemory;

import java.util.Set;

public interface Subscribable {

    void handle(Exchange<?> event);

    Set<Class<?>> supports();
}
