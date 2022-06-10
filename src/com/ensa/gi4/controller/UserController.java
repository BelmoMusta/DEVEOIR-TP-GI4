package com.ensa.gi4.controller;

import com.ensa.gi4.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final Scanner scanner = new Scanner(System.in);
    final UserService userService;

    public void loginUser(){
        do {
            System.out.print("Identifiant : ");
            String username = scanner.next();
            System.out.print("Mot de passe : ");
            String password = scanner.next();
            userService.loginUser(username, password);
            if(!userService.isLogged())
                System.out.println("Identifiant ou mot de passe est incorrect !");
        }while (!userService.isLogged());
        System.out.printf("Welcome, %s !\n", userService.getCurrentUser().getUsername());

    }

}
