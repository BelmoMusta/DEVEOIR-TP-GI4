package com.ensa.gi4.controller;

import com.ensa.gi4.crypto.TrippleDes;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.SignInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


import java.util.Scanner;

@Component("signInController")
public class SignInController {

    @Autowired
    @Qualifier("signInUserService")
    SignInUserService signInUserService;

    @Value("${alert.signIn}")
    String incorrectData;

    @Value("${alert.invalidChoice}")
    String invalidChoice;

    @Autowired
    @Qualifier("controllerPrincipal")
    GestionMaterielController gestionMaterielController;


    Scanner scanner = new Scanner(System.in);

    public void SignIn(){
        System.out.println("Entrez votre Username : \n");
        String username = this.scanner.next();
        System.out.println("Entrez votre mot de passe : \n");
        String password = this.scanner.next();
        //TODO: TRY CATCH BLOC EXCEPTION EMPTY_RESULT_DATA_ACCEES WHEN ENTRING INVALIDE USERNAME OR PASSWORD


        try{
            signInUserService.signInWithUsernameAndPassword(username, password/*TrippleDes.encrypt(password)*/);
            System.out.println("Bonjour \t"+signInUserService.getCurrentUser().getUsername());
            this.showMenuOnRole();
        }
        catch(EmptyResultDataAccessException e){
            this.onIncorrectData();
        }



           /* if(this.signInUserService.getCurrentUser()==null){
                this.onIncorrectData();
             }else {
                System.out.println("Bonjour \t"+signInUserService.getCurrentUser().getUsername());
                this.showMenuOnRole();
            }*/
    }


    public void showMenuOnRole(){
        User currentUser = signInUserService.getCurrentUser();
        if (currentUser == null){
            System.out.println("User is not set yet");
        }
        else{
            System.out.println("\nconnecté en tant que :\t  " + currentUser.getRole()+'\n');
        }
        System.out.println("Chargement .... .\n");
        if(currentUser.isUserAdmin()){
            gestionMaterielController.afficherMenuAdmin();
        }else{
            gestionMaterielController.afficherMenuUser();
        }
    }
    public void onIncorrectData(){
        System.out.println(this.incorrectData);
        String entry = scanner.next();
        switch(entry) {
            case "1":
                this.SignIn();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println(this.invalidChoice);
                this.onIncorrectData();
        }
    }
}
