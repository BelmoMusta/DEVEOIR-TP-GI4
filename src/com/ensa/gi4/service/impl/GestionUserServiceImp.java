package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userService")
public class GestionUserServiceImp implements GestionUserService {
    @Autowired
    UserDao userDao;

    @Override
    public User login(String name, String password) {
      return   userDao.login(name,password);
    }

    @Override
    public void register(String name, String password) {
        userDao.register(name,password);
    }

    @Override
    public void allouer(int idUs,String name, int qte, int nb_days) {
        System.out.println(userDao.allouer(idUs,name,qte,nb_days));
    }

    @Override
    public void rendre(String name) {
        System.out.println(userDao.rendre(name));
    }
}
