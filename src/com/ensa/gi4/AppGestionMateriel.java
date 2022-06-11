package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;
    private static MaterielDao dao;

    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");
       


        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            gestionMaterielController.MENU();
        }

    }
}
