package com.ensa.test;


import com.ensa.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/com/ensa/beans.xml");
        System.out.println("application context loaded..");
        final Controller Controller = context.getBean(com.ensa.controller.Controller.class);

        while (true) { // pour que l'appliation tourne jusqu'à la demande de l'utilisateur de l'arrêter
            Controller.affichermenu();
        }

    }
}
