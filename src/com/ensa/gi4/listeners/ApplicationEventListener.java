package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {

    @Value("${add.livre}")
    private String addLivre;
    @Value("${add.chaise}")
    private String addChaise;
    @Value("${update.livre}")
    private String updateLivre;
    @Value("${update.chaise}")
    private String updateChaise;

    @Value("${remove.livre}")
    private String removeLivre;

    @Value("${remove.chaise}")
    private String removeChaise;
    @Override
    public void onApplicationEvent(MyEvent<T> event) {

        if(event.getEventType().toString().equals("ADD"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println(addLivre);
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println(addChaise);
            }
        }
        if(event.getEventType().toString().equals("UPDATE"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println(updateLivre);
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println(updateChaise);
            }
        }
        if(event.getEventType().toString().equals("REMOVE"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println(removeLivre);
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println(removeChaise);
            }
        }
    }
}

