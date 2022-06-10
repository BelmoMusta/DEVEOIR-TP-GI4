package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielController {
    @Value("${materiel.types}")
    private String TYPES;
    private final Scanner scanner  = new Scanner(System.in);
    final ApplicationPublisher publisher;
    final I18nService i18nService;
    final GestionMaterielService gestionMaterielService;


    public void listerMateriel(){
        gestionMaterielService.listerMateriel();
    }

    public void ajouterMaterile(){

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

    public void chercherMateriel(){
        System.out.println("Search criteria: " + i18nService.getText("table.header").toLowerCase());
        System.out.print(i18nService.getText("prompt.input.search"));
        scanner.nextLine(); //Consume \n
        String search = scanner.nextLine();
        gestionMaterielService.chercherMateriel(search);
    }

    public void supprimerMateriel(){
        System.out.print(i18nService.getText("prompt.input.materielid"));
        gestionMaterielService.supprimerMateriel(scanner.nextInt());
    }

    public void modifierMateriel(){
        System.out.print(i18nService.getText("prompt.input.materielid"));
        gestionMaterielService.modifierMateriel(scanner.nextInt());
    }

    public void allouerMateriel(){
        System.out.print(i18nService.getText("prompt.input.materielid"));
        int id = scanner.nextInt();
        gestionMaterielService.allouerMateriel(id);
    }

    public void desallouerMateriel(){
        System.out.print(i18nService.getText("prompt.input.materielid"));
        int id = scanner.nextInt();
        gestionMaterielService.desallouerMateriel(id);
    }

    public void listerMaterielAlloue(){
        gestionMaterielService.listerMaterielAlloue();
    }

    public void  listerAllocationsHistory(){
        int input;
        do{
            System.out.print(i18nService.getText("prompt.input.materiel.groupby"));
            input = scanner.nextInt();
        }while(input!=1 && input!=2);
        gestionMaterielService.listerAllocationHistory(input);
    }




    public void afficherMenuGestion() {
        boolean exit = false;
        while(true){
            System.out.println(i18nService.getText("menu.gestion.materiel"));
            System.out.format(i18nService.getText("prompt.select.number"));
            switch(scanner.nextInt()){
                case 1 -> listerMateriel();
                case 2 -> ajouterMaterile();
                case 3 -> chercherMateriel();
                case 4 -> supprimerMateriel();
                case 5 -> modifierMateriel();
                case 6 -> afficherMenuAllocation();
                case 0 -> exit = true;
            }
            if (exit) break;
        }
    }

    public void afficherMenuAllocation(){
        boolean exit = false;
        while (true){
            System.out.println(i18nService.getText("menu.gestion.materiel.allocation"));
            System.out.format(i18nService.getText("prompt.select.number"));
            switch(scanner.nextInt()){
                case 1 -> allouerMateriel();
                case 2 -> desallouerMateriel();
                case 3 -> listerMaterielAlloue();
                case 4 -> listerAllocationsHistory();
                case 0 -> exit = true;
            }
            if (exit) break;
        }
    }


}
