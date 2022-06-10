package com.ensa.listeners;

import org.springframework.context.ApplicationEvent;

public class LivreEvent extends ApplicationEvent
{
    private static final long serialVersionUID = 1L;

    private String eventType;
    private String nomlivre;

    public LivreEvent(Object source, String eventType, String nomlivre)
    {
        super(source);
        this.eventType = eventType;
        this.nomlivre = nomlivre;
    }

    public String getEventType() {
        return eventType;
    }

    public String getLivre() {
        return nomlivre;
    }
}
