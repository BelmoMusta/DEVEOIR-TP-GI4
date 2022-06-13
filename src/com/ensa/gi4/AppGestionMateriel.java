package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.controller.GestionUtilisateurController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Utilisateur;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;

    static { // bloc static pour initilialisation

       APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    public static void main(String[] args) {
        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");
        final GestionUtilisateurController gestionUtilisateurController = (GestionUtilisateurController) APPLICATION_CONTEXT.getBean("controllerUser");
      
      
        	// pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            //gestionMaterielController.afficherMenu();
       
           Utilisateur	user=gestionUtilisateurController.login();

        	while(true) {
            gestionUtilisateurController.afficherMenu(user);
            gestionUtilisateurController.gestionChoix(user);
            }

        }
         

       
     }
 
