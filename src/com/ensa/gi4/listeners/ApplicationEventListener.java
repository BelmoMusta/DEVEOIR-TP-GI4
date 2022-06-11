package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Materiel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
    @Override
    public void onApplicationEvent(MyEvent<T> event) {
        System.out.println("Event triggered");
        System.out.println("event.getEventType() = " + event.getEventType());
        System.out.println("event.getSource() = " + event.getSource());
        if(event.getEventType().toString().equals("ADD")){
            if(event.getSource() instanceof Materiel){
                System.out.println("ADD a Material");
            }
        }
        if(event.getEventType().toString().equals("UPDATE")) {
            if(event.getSource() instanceof Materiel){
                System.out.println("UPDATE a Material");
            }
        }
        if(event.getEventType().toString().equals("REMOVE")){
            if(event.getSource() instanceof Materiel){
                System.out.println("REMOVE a Material");
            }
        }

    }
}
