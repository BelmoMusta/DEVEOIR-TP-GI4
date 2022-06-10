package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.datatabase.impl.PersonRowMapper;
import com.ensa.gi4.modele.Person;
import com.ensa.gi4.service.api.ISignIn;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Component
//@Aspect
public class Register extends GenericDAO<Person> implements ISignIn {

    String username;
    String password;
    boolean tmp ;

    @Autowired
    MaterielDaoImpl materielDao;

//    @Before("execution(* com.ensa.gi4.controller.GestionMaterielController.afficherMenu())")
    public Person signInPerson()
    {

        while(true)
        {
            tmp = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pour se conneter, entrer vos coordonnées:");
            System.out.println("Username : ");
            username = scanner.next();
            System.out.println("Password : ");
            password = scanner.next();
            password = doHashing(password);

            try{
                Person person = super.findRealPassword("SELECT * FROM USER WHERE username=?;", username);
                String realPassword = person.getPassword();
//                if(tmp==false){
                   realPassword = doHashing(realPassword);
                    if(realPassword.equals(password))
                    {
//                        tmp=true; //for signup function
                        return person;
                    }
                    else
                    {
                        System.out.println("Password incorrect!");
                    }
//                }
//                else{
//                    if(realPassword.equals(password))
//                    {
//                        return person;
//                    }
//                    else
//                    {
//                        System.out.println("Password incorrect!");
//                    }
//                }

            }
            catch(Exception e){
                System.out.println("Pas d'utilisateur avec ces données!");
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

//    public Person signUpPerson()
//    {
//
//        while(true)
//        {
//            //tmp =false;
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Username : ");
//            username = scanner.next();
//            System.out.println("Password : ");
//            password = scanner.next();
//            if(tmp==false)
//            {
//                password = doHashing(password);
//                tmp = true;
//            }
//            super.ajouterUser("INSERT INTO USER(username,password,role) VALUES (?,?,'user')", username,password);
//            System.out.println("Votre compte est ajouté avec succés!");
//            return signInPerson();
//
//        }
//    }

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
    protected RowMapper<Person> getRowMapper() {
        return new PersonRowMapper();
    }
}
