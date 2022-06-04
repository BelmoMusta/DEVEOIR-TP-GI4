package com.ensa.gi4;

import com.ensa.gi4.controller.ApplicationController;
import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.controller.UserController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings("all")
@ComponentScan(basePackages = "com.ensa.gi4")
@PropertySource("application.properties")
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;

    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        final ApplicationController applicationController = APPLICATION_CONTEXT.getBean(ApplicationController.class);
        applicationController.afficherMessageBienvenue();
        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            applicationController.afficherMenuPrincipale();
        }

    }
}
