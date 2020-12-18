package com.hamzajg.quicktest.sharedkernel.messaging.inmemory;

import java.util.List;

public interface Bus {

    void register(Subscribable subscribable);

    void dispatch(Exchange<?> event);

    List<Subscribable> getSubscribers();
}
