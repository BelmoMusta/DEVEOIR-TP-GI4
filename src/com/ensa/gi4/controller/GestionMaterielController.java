package com.ensa.gi4.controller;

import com.ensa.gi4.enums.Role;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired
    ApplicationPublisher publisher;
    Scanner sc = new Scanner(System.in);

    public void showAdminMenu() {
        System.out.println("0- To show all materials enter 0");
        System.out.println("1- To find a material enter 1");
        System.out.println("2- To add a new material enter 2");
        System.out.println("3- To delete a new material enter 3");
        System.out.println("4- To update a material enter 4");
        System.out.println("5- To borrow a material enter 5");
        System.out.println("6- To return a material enter 6");
        System.out.println("7- To show your allocations enter 7");
        System.out.println("8- To show materials allocated by a user enter 8");
        System.out.println("9- To close application enter 9");
        System.out.println("10- To logout enter 10");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        switch (next)
        {
            case "0":
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                break;
            case "9":
                break;
            case "10":
                break;
            default:

        }
    }

    public void showEmployeeMenu() {
        System.out.println("0- To show all materials enter 0");
        System.out.println("1- To find a material enter 1");
        System.out.println("2- To borrow a material enter 2");
        System.out.println("3- To return a material enter 3");
        System.out.println("4- To show your allocations enter 4");
        System.out.println("5- To show all materials enter 5");
        System.out.println("6- To close application enter 6");
        System.out.println("7- To logout enter 7");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        switch (next)
        {
            case "0":
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            default:

        }
    }

    public void afficherMenu(User user) {
        System.out.println("***********Menu***********");
        if(user.getRole_id() == Role.ADMIN.ordinal()) {
            while(true)
            {
                showAdminMenu();
            }
        }
        else {
            while(true)
            {
                showEmployeeMenu();
            }
        }
    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
