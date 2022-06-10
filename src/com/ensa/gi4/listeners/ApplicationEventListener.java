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
            System.out.println("\nLe materiel '" + materiel.getName() + "' est ajouté avec une quantité égale à " + materiel.getQuantity() + ".");
        } else if (eventType == EventType.ADDq) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLa quantité du materiel '" + materiel.getName() + "' est devenue " + materiel.getQuantity() + ".");
        } else if (eventType == EventType.UPDATEn) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLe nom du materiel '" + materiel.getName() + "' est modifié avec succès.");
        } else if (eventType == EventType.UPDATEc) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLe code du materiel '" + materiel.getName() + "' est modifié avec succès.");
        } else if (eventType == EventType.UPDATEa) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLa disponibilité du materiel '" + materiel.getName() + "' est modifiée avec succès.");
        } else if (eventType == EventType.UPDATEq) {
            Materiel materiel = (Materiel) eventSource;
            System.out.println("\nLa quantité du materiel '" + materiel.getName() + "' est modifiée avec succès.");
        }
    }
}
