package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerEmailChanged implements Event {
    public CustomerEmailChanged(UUID id, String newEmail) {

    }
}
