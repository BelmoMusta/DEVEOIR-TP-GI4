package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userService")
public class GestionUserServiceImpl implements GestionUserService {

    @Autowired
    UserDao userDao;

    @Override
    public User cherhcerUtilisatuer(String username, String password) {
        return userDao.findUser(username, password);
    }
}
