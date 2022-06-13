package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Material;
import org.springframework.context.ApplicationEvent;

public class MyEvent<T extends Material> extends ApplicationEvent {

    private final EventType eventType;

    public MyEvent(T source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
