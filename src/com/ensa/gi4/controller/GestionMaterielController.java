package com.ensa.gi4.controller;

import com.ensa.gi4.enums.Role;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthenticationService;
import com.ensa.gi4.service.api.MaterialsManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {



    @Autowired
    MaterialsManagingService materialsManagingService;
    @Autowired
    AuthenticationController authenticationController;
    Scanner sc = new Scanner(System.in);

    public void showAdminMenu(int userId) {
        System.out.println("0- To show all materials enter 0");
        System.out.println("1- To find a material enter 1");
        System.out.println("2- To add a new material enter 2");
        System.out.println("3- To delete a material enter 3");
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
                materialsManagingService.listAllMaterials();
                break;
            case "1":
                materialsManagingService.showMaterial();
                break;
            case "2":
                materialsManagingService.addMaterial();
                break;
            case "3":
                materialsManagingService.deleteMaterial();
                break;
            case "4":
                materialsManagingService.updateMaterial();
                break;
            case "5":
                materialsManagingService.borrowMaterial(userId);
                break;
            case "6":
                materialsManagingService.returnMaterial(userId);
                break;
            case "7":
                materialsManagingService.showMaterialsBorrowedByMe(userId);
                break;
            case "8":
                materialsManagingService.showMaterialsBorrowedByAUser();
                break;
            case "9":
                exitApplication();
                break;
            case "10":
                authenticationController.showLoginMenu();
                break;
            default:

        }
    }

    public void showEmployeeMenu(int userId) {
        System.out.println("0- To show all materials enter 0");
        System.out.println("1- To find a material enter 1");
        System.out.println("2- To borrow a material enter 2");
        System.out.println("3- To return a material enter 3");
        System.out.println("4- To show your allocations enter 4");
        System.out.println("5- To close application enter 5");
        System.out.println("6- To logout enter 6");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        switch (next)
        {
            case "0":
                materialsManagingService.listAllMaterials();
                break;
            case "1":
                materialsManagingService.showMaterial();
                break;
            case "2":
                materialsManagingService.borrowMaterial(userId);
                break;
            case "3":
                materialsManagingService.returnMaterial(userId);
                break;
            case "4":
                materialsManagingService.showMaterialsBorrowedByMe(userId);
                break;
            case "5":
                exitApplication();
                break;
            case "6":
                authenticationController.showLoginMenu();
                break;
            default:

        }
    }

    public void showMenu(User user) {
        System.out.println("***********Menu***********");
        if(user.getRole_id() == Role.ADMIN.ordinal()) {
            while(true)
            {
                showAdminMenu(user.getUser_id());
            }
        }
        else {
            while(true)
            {
                showEmployeeMenu(user.getUser_id());
            }
        }
    }

    private void exitApplication() {
        System.exit(0);
    }

}
