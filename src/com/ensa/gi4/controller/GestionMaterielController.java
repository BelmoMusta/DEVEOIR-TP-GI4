package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.RoleDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

@Controller("controllerMateriel")
@Lazy
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielController {
    @Value("${materiel.types}")
    private String TYPES;
    private final Scanner scanner  = new Scanner(System.in);
    final ApplicationPublisher publisher;
    final I18nService i18nService;
    final GestionMaterielService gestionMaterielService;


    void listerMateriel(){
        gestionMaterielService.listerMateriel();
    }

    void ajouterMaterile(){
        String[] types_materiel = TYPES.split(",");
        System.out.print(i18nService.getFormattedText("prompt.select.type", TYPES));
        String type = scanner.next().toUpperCase();
        scanner.nextLine(); //to consume \n of the previous input

        if (!Arrays.asList(types_materiel).contains(type)){
            System.out.println(i18nService.getText("message.wrong.input"));
            return;
        }

        String[] fields = i18nService.getText("materiel." + type.toLowerCase() + ".input.fields").split(",");
        for(int i =0; i<fields.length; i++){
            if(fields[i].equals("Type")) fields[i] = type;
            else {
                System.out.print(i18nService.getFormattedText("prompt.input.value", fields[i]));
                fields[i] = scanner.nextLine();
            }
        }
        gestionMaterielService.ajouterNouveauMateriel(fields);

    }

    void chercherMateriel(){
        //TODO : chercherMateriel()
    }

    void supprimerMateriel(){
        //TODO : supprimerMateriel()
    }

    //TODO : Allocation d'un materiel




    public void afficherMenu() {
        boolean breakFromWhile = false;
        while(true){
            System.out.println(i18nService.getText("menu.gestion.materiel"));
            System.out.format(i18nService.getText("prompt.select.number"));
            switch(scanner.nextInt()){
                case(1) -> listerMateriel();
                case(2) -> ajouterMaterile();
                case(3) -> chercherMateriel();
                case(4) -> supprimerMateriel();
                case(0) -> breakFromWhile = true;
            }
            if (breakFromWhile) break;
        }





    }

    private void sortirDeLApplication() {
        System.exit(0);
    }

}
