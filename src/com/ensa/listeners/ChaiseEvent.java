package com.ensa.listeners;

import org.springframework.context.ApplicationEvent;

public class ChaiseEvent extends ApplicationEvent
    {
        private static final long serialVersionUID = 1L;

        private String eventType;
        private String nomchaise;

        public ChaiseEvent(Object source, String eventType, String nomlivre)
        {
            super(source);
            this.eventType = eventType;
            this.nomchaise = nomlivre;
        }

        public String getEventType() {
            return eventType;
        }

        public String getChaise() {
            return nomchaise;
        }
    }


