package com.ensa.gi4;

import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;
    static Optional<User> checkUser; 
    
    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");
   	
       Optional<String> checkChoice =  gestionMaterielController.greeting();
       
       if (checkChoice.isPresent()) {
    	   
    	   switch (checkChoice.get()) {
				case "1":
					do {
						List<String> userData = gestionMaterielController.getUserData(); 
			        	checkUser = gestionMaterielController.authentification(userData);
					} while (!checkUser.isPresent());
					break;
				case "2":
					List<String> userData = gestionMaterielController.getUserData();
					checkUser =  gestionMaterielController.signUp(userData); 
					break; 
				default:
					System.out.println("Choix invalide !");
					System.exit(0);
					break;
		}
    	
    	   
    	   while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
               gestionMaterielController.afficherMenu(checkUser.get());
            }
	}
    	

    }
}
