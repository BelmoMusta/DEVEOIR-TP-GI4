package com.ensa.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LivreEventsProcessor implements ApplicationListener<LivreEvent>
{
    public void onApplicationEvent(LivreEvent event)
    {
        LivreEvent livreEvent = (LivreEvent) event;

        System.out.println(livreEvent.getEventType()+":Livre,nom:"+livreEvent.getLivre());


    }
}
