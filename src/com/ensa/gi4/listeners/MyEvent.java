package com.ensa.gi4.listeners;
import com.ensa.gi4.modele.*;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.listeners.EventType;

import org.springframework.context.ApplicationEvent;

public class MyEvent<T extends Materiel> extends ApplicationEvent {

    private final EventType eventType;

    public MyEvent(T source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
