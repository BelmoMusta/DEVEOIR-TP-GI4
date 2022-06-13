package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;

@Component("controllerPrincipal")
public class GestionMaterielController {
User user;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    GestionMaterielService gestionMaterielService;
    public User UserLogin() {
        System.out.print("Name : ");
        Scanner scanner  = new Scanner(System.in);
        String name = scanner.next();
        System.out.print("Password : ");
        String password = scanner.next();
        user = userDao.Login(name, password);
        return user;
    }


    public void afficherMenu(User user) {
        if(Objects.equals(user.getRole(), "ADMIN")) {
            adminMenu();
        } else {
            userMenu();
        }
    }



    private void adminMenu() {

        System.out.println("1- Find material");
        System.out.println("2-Add material");
        System.out.println("3- Delete material");
        System.out.println("4- Edit material");
        System.out.println("5- Mark material as not available");
        System.out.println("6- Rent material");
        System.out.println("7- Return material");
        System.out.println("8- List material Rented by user");
        System.out.println("9- List all Allocated material");
        System.out.println("10- List all materials");
        System.out.println("0- Exit app");

        Scanner scanner  = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        }else if("1".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.SearchMaterial(materialId);
        }else if("2".equals(next)) {
            System.out.print("Enter the material's name : ");
            String name = scanner.next();
            System.out.print("Enter the material's type : ");
            String materialType = scanner.next();
            Material material=new Material();
            material.setName(name);
            material.setMaterialType(materialType);
            gestionMaterielService.AddNewMaterial(material);
        }else if("3".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.DeleteMaterial(materialId);
        }else if("4".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            System.out.print("New name : ");
            String name =scanner.next();
            System.out.print("New type : ");
            String materialType =scanner.next();
            gestionMaterielService.EditMaterial(materialId, name, materialType);}
        else if("5".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.MarkNotAvailable(materialId);
        }else if("6".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.AllocateMaterial(materialId, user.getUserId());
        }else if("7".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.ReturnMaterial(materialId);
        }else if("8".equals(next)) {
            gestionMaterielService.ListUserAllocatedMaterialToUser(user.getUserId());
        }else if("9".equals(next)) {
            gestionMaterielService.ListAllocatedMaterial();
        }else if("10".equals(next)) {
            gestionMaterielService.ListAllMaterial();
        }
    }


    private void userMenu() {

        System.out.println("1- Find material");
        System.out.println("2- Rent material");
        System.out.println("3- Return material");
        System.out.println("4- List material Rented by user");
        System.out.println("5- List all materials");
        System.out.println("0- Exit app");

        Scanner scanner  = new Scanner(System.in);
        String next = scanner.next();
        if ("0".equals(next)) {
            sortirDeLApplication();
        }else if("1".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.SearchMaterial(materialId);
        }else if("2".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.AllocateMaterial(materialId, user.getUserId());
        }else if("3".equals(next)) {
            System.out.print("Enter materialId : ");
            long materialId = scanner.nextLong();
            gestionMaterielService.ReturnMaterial(materialId);
        }else if("4".equals(next)) {
            gestionMaterielService.ListUserAllocatedMaterialToUser(user.getUserId());
        }else if("5".equals(next)) {
            gestionMaterielService.ListAllMaterial();
        }
    }



    private void sortirDeLApplication() {
        System.exit(0);
    }

}
