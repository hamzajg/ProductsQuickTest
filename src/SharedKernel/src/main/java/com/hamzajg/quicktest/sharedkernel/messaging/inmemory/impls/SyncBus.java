package com.hamzajg.quicktest.sharedkernel.messaging.inmemory.impls;

import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Vector;

public class SyncBus implements Bus {

    private List<Subscribable> subscribers;

    public SyncBus() {
        subscribers = new Vector<>();
    }

    @Override
    public void register(Subscribable subscribable) {
        subscribers.add(subscribable);
    }

    @Override
    public void dispatch(final Exchange<?> event) {
        try {
            subscribers.stream()
                    .filter(subscriber -> subscriber.supports().contains(event.getData().getClass()))
                    .forEach(subscriber -> subscriber.handle(event));
        } catch (ConcurrentModificationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Subscribable> getSubscribers() {
        return subscribers;
    }
}
