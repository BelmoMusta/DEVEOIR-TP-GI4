package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.datatabase.impl.UserRowMapper;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.ILogin;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;


@Component
public class Login extends GenericDAO<User> implements ILogin {

    String username;
    String password;
    boolean check ;



    @Override
    public User login() {
        while(true)
        {
            check = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("username : ");
            username = scanner.next();
            System.out.println("password : ");
            password = scanner.next();

            try{
                User user = super.findRealPassword("SELECT * FROM USER WHERE username=?;", username);
                String realPassword = user.getPassword();
                 if(realPassword.equals(password))
                    {
                        return user;
                    }
                    else
                    {
                        System.out.println("password incorrect");
                    }

            }
            catch(Exception e){
                System.out.println("user not found");
                System.out.println("1- pour essayer une nouvelle fois, entrer 1");
                System.out.println("0- pour sortir de l'application, entrer 0");
                String next = scanner.next();
                if ("0".equals(next)) {
                    System.exit(0);
                } else if ("1".equals(next)) {
                    continue;
                }
            }
        }
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
