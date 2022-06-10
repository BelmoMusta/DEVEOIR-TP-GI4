package com.ensa.service.impl;

import com.ensa.dao.UserDao;
import com.ensa.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class UserService {
    @Autowired
    UserDao userdao;

  public  User login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez username");
        String username =scanner.next();
        System.out.println("Entrez password");
        String pass=scanner.next();
      if( userdao.find(username,pass)!=null){
          return userdao.find(username,pass);
      }
      else {return null;}



    }
}
