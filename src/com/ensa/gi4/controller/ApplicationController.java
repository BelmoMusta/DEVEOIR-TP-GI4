package com.ensa.gi4.controller;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
                case(1) -> gestionMaterielController.afficherMenu();
                case(2) -> changerLaLangue();
                case(0) -> System.exit(0);
            }
        }else {
            System.out.println(i18nService.getText("menu.principal.notlogged"));
            System.out.print(i18nService.getText("prompt.select.number"));
            switch (scanner.nextInt()){
                case(1) -> userController.loginHandler();
                case(3) -> changerLaLangue();
                case(0) -> System.exit(0);
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


}
