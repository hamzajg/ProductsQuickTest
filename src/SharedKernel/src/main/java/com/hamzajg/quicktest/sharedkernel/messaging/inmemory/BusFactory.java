package com.hamzajg.quicktest.sharedkernel.messaging.inmemory;

import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.impls.AsyncBus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.impls.SyncBus;

public final class BusFactory {
    public static synchronized Bus createSyncEventBus() {
        return new SyncBus();
    }

    /**
     * creates and returns an SyncEventBus, the instance is held inside an enumeration, to make it a
     * singleton inside the vm
     */
    public static synchronized Bus createSingletonSyncBus() {
        return BusHolder.SYNC_INSTANCE.bus;
    }

    /**
     * the factory does not assume the EventBus to be a singleton, it has another method specific to
     * that each time it is called, an new instance of EventBus is returned
     */
    public static synchronized Bus createAsyncBus() {
        return new AsyncBus();
    }

    /**
     * creates and returns an AsyncEventBus, the instance is held inside an enumeration, to make it a
     * singleton inside the vm
     */
    public static synchronized Bus createSingletonAsyncBus() {
        return BusHolder.ASYNC_INSTANCE.bus;
    }

    private enum BusHolder {

        SYNC_INSTANCE(new SyncBus()),


        ASYNC_INSTANCE(new AsyncBus());

        private Bus bus;

        BusHolder(Bus bus) {
            this.bus = bus;
        }
    }
}
