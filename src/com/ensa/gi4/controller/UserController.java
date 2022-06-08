package com.ensa.gi4.controller;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.I18nService;
import com.ensa.gi4.service.api.UserService;
import enums.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ensa.gi4.utils.hasAuthority;

import java.util.List;
import java.util.Scanner;

@Component("userController")
public class UserController {
    private final Scanner scanner = new Scanner(System.in);
    private final I18nService i18nService;
    private final UserService userService;

    @Autowired
    public UserController(I18nService i18nService, UserService userService) {
        this.i18nService = i18nService;
        this.userService = userService;
    }

    public void loginHandler(){
        String username;
        String password;
        LoginResponse response = null;
        do{
            if (response != null)
                switch (response){
                    case ACCOUNT_LOCKED -> System.out.println(i18nService.getText("login.locked"));
                    case USER_NOT_FOUND -> System.out.println(i18nService.getText("login.username.notfound"));
                    case PASSWORD_INCORRECT -> System.out.println(i18nService.getText("login.password.incorrect"));
                }
            System.out.print(i18nService.getText("login.prompt.username"));
            username = scanner.nextLine();
            System.out.print(i18nService.getText("login.prompt.password"));

            if (System.console() != null){ //l'application est lancée dans un environment de production (console/cmd/shell/...)
                password = String.valueOf(System.console().readPassword());
            }else {
                password = scanner.nextLine();
            }

            response = userService.loginUser(username, password);
        }while (!response.equals(LoginResponse.SUCCESS));
        System.out.println(i18nService.getText("login.success"));
        System.out.println(i18nService.getFormattedText("greeting.registered", username));
    }

    public void confirmUser(){
        String currentUserName = userService.getLoggedUser().getUsername();
        boolean confirmed = false;
        System.out.println(i18nService.getFormattedText("message.info.user.expired", currentUserName));
        do {
            System.out.print(i18nService.getText("login.prompt.password"));
            String password = scanner.nextLine();
            confirmed = this.userService.isPasswordMatch(password);
            if(!confirmed) System.out.println(i18nService.getText("login.password.incorrect"));
            else userService.refreshValidity();
        }while (!confirmed);
    }

    @hasAuthority("ADMIN")
    public void gestionUser(){
        boolean back = false;
        while(true){
            System.out.println(i18nService.getText("menu.gestion.users"));
            System.out.print(i18nService.getText("prompt.select.number"));
            switch (scanner.nextInt()){
                case 1 -> listUsers();
                case 2 -> unlockUser();
                case 3 -> lockUser();
                case 0 -> back = true;
                default -> System.out.println(i18nService.getText("message.wrong.input"));
            }
            if (!back)
                break;;
        }
    }

    @hasAuthority("ADMIN")
    private void listUsers() {
        this.userService.listUsers();
    }

    @hasAuthority("ADMIN")
    private void unlockUser() {
        System.out.print(i18nService.getText("prompt.input.user.id"));
        this.userService.lockUser(scanner.nextInt(), false);
    }

    @hasAuthority("ADMIN")
    private void lockUser() {
        System.out.print(i18nService.getText("prompt.input.user.id"));
        this.userService.lockUser(scanner.nextInt(), true);
    }




}
