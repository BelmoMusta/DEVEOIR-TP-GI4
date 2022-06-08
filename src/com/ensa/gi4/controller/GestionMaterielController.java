package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.impl.GestionUserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired

    ApplicationPublisher publisher;

   @Autowired
    GestionUserImp gestionUserImp;

    public void signUp(){
        System.out.println("WELCOME TO THIS APP PLEASE LOGIN");
        gestionUserImp.userLogin();
    }


    private void sortirDeLApplication() {
        System.exit(0);
    }

}
