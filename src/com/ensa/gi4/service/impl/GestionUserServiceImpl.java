package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component("userService")
public class GestionUserServiceImpl implements GestionUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean trouverUser(User user) {
        User userInput;
        userInput= userDao.findUser(user.getUsername(), user.getPassword());
        if(userInput.getUsername().equals(user.getUsername()) && userInput.getPassword().equals(user.getPassword())) {
            return true;
        }else {
            return false;
        }
    }

   /* public User findUserInfo(String username,String password) {
        return userDao.findUser(username,password);
    }*/

    @Override
    public Boolean trouverRole(User user, String role) {

        return userDao.findRole(user.getUsername()).equals(role);

    }






}
