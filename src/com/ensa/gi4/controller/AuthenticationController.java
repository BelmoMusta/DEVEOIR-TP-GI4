package com.ensa.gi4.controller;

import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthenticationService;
import com.ensa.gi4.utils.PasswordEncryptingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

@Controller("authenticationController")
public class AuthenticationController {

    @Value("${menu.incorrect.credentials}")
    String incorrectCredentialsMenu;

    @Autowired
    User authenticatedUser;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    @Qualifier("adminController")
    GestionMaterialController adminController;
    @Autowired
    @Qualifier("employeeController")
    GestionMaterialController employeeController;

    private Scanner scanner = new Scanner(System.in);

    public void authenticate(){
        System.out.println("Username:");
        String username = this.scanner.nextLine();
        System.out.println("Password:");
        String password = this.scanner.nextLine();
        authenticationService.authenticateWithUsernameAndPassword(username,
                PasswordEncryptingUtil.encryptPassword(password));
        if (authenticatedUser.getId() == null){
            this.incorrectCredentialsMenu();
        }
        String helloMessage = String.format("Hello %s %s\n", authenticatedUser.getFirstName(),
                authenticatedUser.getLastName());
        System.out.println(helloMessage);
        this.redirectBasedOnRole();

    }

    public void checkForAuthenticatedUser() {
        if (authenticatedUser.getId() == null) {
            this.authenticate();
        } else {
            this.redirectBasedOnRole();
        }
    }

    public void redirectBasedOnRole() {
        if (isUserAdmin()) {
            adminController.showMenu();
        } else {
            employeeController.showMenu();
        }
    }

    public void incorrectCredentialsMenu(){
        System.out.println(incorrectCredentialsMenu);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                this.authenticate();
                break;
            case "2":
                System.exit(0);
            default:
                System.out.println("Choix invalide");
                break;
        }
    }

    public boolean isUserAdmin() {
        return authenticationService.isUserAdmin();
    }
}
