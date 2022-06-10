package com.ensa.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChaiseEventsProcessor implements ApplicationListener<ChaiseEvent>
{
    public void onApplicationEvent(ChaiseEvent event)
    {
        ChaiseEvent cEvent = (ChaiseEvent) event;

        System.out.println(cEvent.getEventType()+":Chaise,nom:"+cEvent.getChaise());


    }
}
