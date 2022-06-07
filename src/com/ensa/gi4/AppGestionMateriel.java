package com.ensa.gi4;

import com.ensa.gi4.controller.AuthenticationController;
import com.ensa.gi4.controller.GestionMaterielController;
import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterialsDAO;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SuppressWarnings("all")
@ComponentScan
public class AppGestionMateriel {
    private static final ApplicationContext APPLICATION_CONTEXT;
    private static boolean isAuthenticated = false;

    static { // bloc static pour initilialisation

        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppGestionMateriel.class);
    }

    public static void main(String[] args) {
        final GestionMaterielController gestionMaterielController = (GestionMaterielController) APPLICATION_CONTEXT.getBean("controllerPricipal");
        final AuthenticationController authenticationController = (AuthenticationController) APPLICATION_CONTEXT.getBean("authenticationController");
        final DAO<Material> materialDAO = (DAO<Material>) APPLICATION_CONTEXT.getBean("materialsDao");
        Scanner scanner  = new Scanner(System.in);



        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
//            int value = scanner.nextInt();
//            if(value == 1)
//                System.out.println("IsAuthenticated" + isAuthenticated);
//            else  isAuthenticated = !isAuthenticated;
//            //gestionMaterielController.afficherMenu();

           // authenticationController.showLoginMenu();
             materialDAO.getAll().forEach(System.out::println);

        }

    }
}
