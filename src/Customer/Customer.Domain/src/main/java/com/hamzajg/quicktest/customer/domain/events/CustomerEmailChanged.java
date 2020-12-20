package com.hamzajg.quicktest.customer.domain.events;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerEmailChanged implements Event {
    public CustomerEmailChanged(UUID id, String newEmail) {

    }
}
