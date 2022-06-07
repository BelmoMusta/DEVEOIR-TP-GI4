package com.ensa.gi4.controller;

import com.ensa.gi4.enums.Role;
import com.ensa.gi4.enums.UserCreateStatus;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("authenticationController")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    @Qualifier("materielControllerBean")
    GestionMaterielController gestionMaterielController;

    public void showLoginMenu()
    {
        String username;
        String password;
        System.out.println("Type 1 to login");
        System.out.println("Type 2 to register");
        int code = scanner.nextInt();
        if(code == 1)
        {
            System.out.println("**********Login***********\n");
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();

            User user =  authenticationService.getUser(password,username);

            if(user == null)
            {
                System.out.println("Wrong password or username\n");
                System.out.println("Type -1 to exit");
                System.out.println("Type 1 to retry");
                int code2 = scanner.nextInt();
                if( code2 == -1)
                    System.exit(0);

                showLoginMenu();
            }

            gestionMaterielController.afficherMenu(user);
        }
        else if(code == 2 )
        {
            System.out.println("**********Register***********\n");
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            System.out.println("Role, Type 1 for ADMIN, 2 for EMPLOYEE");
            int role = scanner.nextInt();

            UserCreateStatus status =  authenticationService.createUser(username,password, Role.values()[role]);
            switch(status)
            {
                case CREATED_SUCCESSFULLY:
                    System.out.println("User created successfully");
                    break;
                case DATABASE_ERROR:
                    System.out.println("Server Error");
                    break;
                case USER_EXISTS:
                    System.out.println("A user with this username already exists");

            }
        }


    }

}
