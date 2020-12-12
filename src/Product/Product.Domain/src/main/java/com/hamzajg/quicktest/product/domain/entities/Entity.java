package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public abstract class Entity {
    protected UUID id;
    protected Collection<Event> eventCollection;
    protected Entity() {
        id = UUID.randomUUID();
       eventCollection = new ArrayList<>();
    }
    protected void addEvent(Event event){
        eventCollection.add(event);
    }
}
