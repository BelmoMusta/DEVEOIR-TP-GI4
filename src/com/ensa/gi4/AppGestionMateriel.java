package com.ensa.gi4;

import com.ensa.gi4.controller.AuthenticationController;
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
        final AuthenticationController authenticationController =
                (AuthenticationController) APPLICATION_CONTEXT.getBean("authenticationController");
        System.out.println("Bienvenue sure notre application");
        System.out.println("Saisir votre compte s'il vous plait\n");
        while (true) {
            authenticationController.checkForAuthenticatedUser();
        }

    }
}
