package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.datatabase.impl.personneRowMapper;
import com.ensa.gi4.modele.Admin;
import com.ensa.gi4.modele.personne;
import com.ensa.gi4.service.api.GestionPersonService;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

//@Aspect
@Component("personneService")
public class GestionPersonServiceImpl extends GenericDAO<personne> implements GestionPersonService {

    String username;
    String password;
    boolean tmp ;


   // @Pointcut("execution(* com.ensa.gi4.controller.GestionMaterielController.afficherMenu())")
   // public void methodCall() {};
    @Override
   // @Before("methodCall()")
    public personne signInPerson() {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Pour se conneter, entrer vos coordonnées:");
            System.out.println("Username : ");
            username = scanner.next();
            System.out.println("Password : ");
            password = scanner.next();
            //password = doHashing(password);

            try {
                personne person = super.executeQuery("SELECT * FROM person WHERE name='"+ username + "' AND password= '"+password+"';");

                    return person;

            } catch (Exception e) {
                System.out.println("Pas d'utilisateur avec ces données!");
                return null;
            }
        }



    public String doHashing(String password)
    {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : resultByteArray)
            {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    protected RowMapper<personne> getRowMapper() {
        return new personneRowMapper();
    }
}
