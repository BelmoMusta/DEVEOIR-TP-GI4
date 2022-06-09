package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Materiel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
    @Override
    public void onApplicationEvent(MyEvent<T> event) {

        Object eventSource = event.getSource();
        EventType eventType = event.getEventType();

        if (eventType == EventType.ADD) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLe materiel " + materiel.getName() + " est ajouté avec succès");
        } else if (eventType == EventType.UPDATE) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLe materiel " + materiel.getName() + " est modifié avec succès");
        }
    }
}
