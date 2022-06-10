package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@ComponentScan(basePackages = "com.ensa.gi4")
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;

    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {



        // (admin) : username = aymane, password = aymane0101
        // (user) : username = nisrine, password = nisrine0101



        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");

        User user = null;
        Boolean userValid = false;

        do {

            try {

                List<String> userCredentials = gestionMaterielController.getUserCredentials();
                user = gestionMaterielController.authentification(userCredentials);
                System.out.println("Bon retour");
                System.out.println("vous êtes : " + user.getRole());
                userValid = true;

            } catch (Exception e) {

                System.out.println("Utilisateur invalide, veuillez ressayer");

            }

        } while (userValid == false);



        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            gestionMaterielController.afficherMenu(user);
        }

    }
}
