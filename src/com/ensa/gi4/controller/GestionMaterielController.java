package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;

    public void afficherMenu() {
        System.out.println("***********Menu***********");

        Scanner scanner  = new Scanner(System.in);
        scanner.nextLine();
        //publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
