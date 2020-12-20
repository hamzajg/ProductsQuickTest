package com.hamzajg.quicktest.customer.domain.events;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerMobileChanged implements Event {
    public CustomerMobileChanged(UUID id, String newMobile) {
    }
}
