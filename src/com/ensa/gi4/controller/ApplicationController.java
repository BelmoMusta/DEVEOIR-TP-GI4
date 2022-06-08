package com.ensa.gi4.controller;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.ensa.gi4.service.impl.I18nServiceImpl.LANGUAGES_LIST;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationController {
    private Scanner scanner = new Scanner(System.in);
    private final GestionMaterielController gestionMaterielController;
    private final UserController userController;
    private final I18nService i18nService;
    private final UserService userService;

    public void afficherMenuPrincipale(){
        User user = userService.getLoggedUser();
        if (user != null && !userService.isUserExpired()){
            System.out.println(i18nService.getText("menu.principal.logged"));
            System.out.print(i18nService.getText("prompt.select.number"));
            switch (scanner.nextInt()){
                case(1) -> gestionMaterielController.afficherMenuGestion();
                case(2) -> changerLaLangue();
                case(3) -> userController.gestionUser();
                case(0) -> System.exit(0);
                default -> System.out.println(i18nService.getText("message.wrong.input"));
            }
        }else {
            System.out.println(i18nService.getText("menu.principal.notlogged"));
            System.out.print(i18nService.getText("prompt.select.number"));
            switch (scanner.nextInt()){
                case(1) -> userController.loginHandler();
                case(2) -> creerCompte();
                case(3) -> changerLaLangue();
                case(0) -> System.exit(0);
                default -> System.out.println(i18nService.getText("message.wrong.input"));
            }
        }


    }

    public void afficherMessageBienvenue(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(i18nService.getFormattedText("greeting.general", LocalDateTime.now().format(formatter)));
    }

    public void changerLaLangue(){
        for(int i=0; i<LANGUAGES_LIST.length; i++){
            System.out.format("[%d] %s\n", i, LANGUAGES_LIST[i]);
        }
        System.out.print(i18nService.getText("prompt.select.language") + ": ");
        this.i18nService.setLanguage(LANGUAGES_LIST[scanner.nextInt()]);
    }

    public void creerCompte(){
        scanner.nextLine(); //escaping \n
        String[] fields = i18nService.getText("user.input.fields").split(",");
        String[] values = new String[fields.length];
        String[] regex = new String[]{"\\w+", "\\w+@[a-zA-Z]+\\.[a-zA-Z]{2,5}", ".{3,}"};
        boolean isValid;
        for(int i =0; i< fields.length; i++){
            do{
                System.out.print(i18nService.getFormattedText("prompt.input.value", fields[i]));
                values[i] = scanner.nextLine();
                isValid = !values[i].matches(regex[i]);
                if(isValid)
                    System.out.println(i18nService.getText("message.wrong.input"));
            }while(isValid);
        }
        User newUser = this.userService.addUser(values[0], values[1], values[2]);
        System.out.println("User created success, id = " + newUser.getId());
    }

}
