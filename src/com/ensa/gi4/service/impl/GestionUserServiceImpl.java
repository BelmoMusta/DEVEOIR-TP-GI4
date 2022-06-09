package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GestionUserServiceImpl implements GestionUserService {
@Autowired
    UserDao userDao;


    @Override
    public List<User> listerUser() {
        return userDao.userList();
    }

    @Override
    public List<User> listerRole(User user) {
        return userDao.listRoles(user);
    }
}
