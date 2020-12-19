package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerMobileChanged implements Event {
    public CustomerMobileChanged(UUID id, String newMobile) {
    }
}
