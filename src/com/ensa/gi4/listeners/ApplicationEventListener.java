package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
    @Override
    public void onApplicationEvent(MyEvent<T> event) {

        if(event.getEventType().toString().equals("ADD"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println( "Livre ajouté");
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println("Chaise ajoutée ");
            }
        }
        if(event.getEventType().toString().equals("UPDATE"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println("Livre modifié");
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println("Chaise modifiée");
            }
        }
        if(event.getEventType().toString().equals("REMOVE"))
        {
            if(event.getSource() instanceof Livre)
            {
                System.out.println( "Livre supprimé ");
            }
            else if(event.getSource() instanceof Chaise)
            {
                System.out.println("REMOVE supprimé");
            }
        }
    }

}
