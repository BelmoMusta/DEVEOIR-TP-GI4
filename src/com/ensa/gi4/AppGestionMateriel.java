package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;

    static {
        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");

        System.out.println("-----------------------------------");
        System.out.println("Bonjour, veuillez vous authentifier");
        System.out.println("-----------------------------------");

        gestionMaterielController.authentification();
    }
}
