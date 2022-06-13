package com.ensa.gi4;

import com.ensa.gi4.controller.SignInController;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.controller.GestionMaterielController;

import com.ensa.gi4.service.api.SignInUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings("all")
@ComponentScan(basePackages="com.ensa.gi4")
//@PropertySource(value="classpath:application.properties") giving file not found exception

public class AppGestionMateriel {
   private static final ApplicationContext APPLICATION_CONTEXT;
    //private static AnnotationConfigApplicationContext ctx;
    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    
    }

    public static void main(String[] args) {
        /*ctx = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
        //ctx.register(PropertiesConfigurer.class);
        //ctx.register(DatabaseConfig.class);
        ctx.register(AppConfig.class);
        ctx.refresh();*/


        //TODO :ARBITRARY PROBLEM IN SQL STAREMENTS :/


        //final SignInUserService signInUserService =(SignInUserService) APPLICATION_CONTEXT.getBean("signInUserService");
        final SignInController signInController = (SignInController) APPLICATION_CONTEXT.getBean("signInController");
       //final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPrincipal");
        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            signInController.SignIn();
        }

    }
}
