package com.hamzajg.quicktest.sharedkernel.messaging.inmemory.impls;

import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Subscribable;

import java.util.List;
import java.util.Vector;

public class AsyncBus implements Bus {

    private List<Subscribable> subscribers;

    public AsyncBus() {
        subscribers = new Vector<>();
    }

    @Override
    public void register(Subscribable subscribable) {
    }

    @Override
    public void dispatch(Exchange<?> event) {

    }

    @Override
    public List<Subscribable> getSubscribers() {
        return subscribers;
    }
}
