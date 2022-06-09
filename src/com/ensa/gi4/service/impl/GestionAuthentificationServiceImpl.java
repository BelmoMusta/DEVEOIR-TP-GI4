package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authentificationService")
public class GestionAuthentificationServiceImpl implements GestionAuthentificationService {

    @Autowired
    UserDao userDao;

    @Override
    public User chercherUser(List<String> userCredentials) {
        return userDao.findUser(userCredentials.get(0), userCredentials.get(1));
    }

}
