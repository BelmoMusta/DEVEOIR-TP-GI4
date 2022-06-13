package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.SignInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component("signInUserService")
public class SignInUserServiceImpl implements SignInUserService{

    @Autowired
    UserDao userDao;
    @Autowired
    User currentUser;

    @Override
    public void signInWithUsernameAndPassword(String username, String password) {
        User u = userDao.findUserByUsernameAndPassword(username,password);
        if(u == null)
        { System.out.println("Données Invalides..");
            return;}

        setCurrentUser(u);

    }

    @Override
    public boolean isAdmin(User user) {
        if(user.getRole().equalsIgnoreCase("admin"))
            return true;
        return false;
    }

    @Override
    public void setCurrentUser(User user) {
        currentUser.setIdUser(user.getIdUser());
        currentUser.setUsername(user.getUsername());
        currentUser.setRole(user.getRole());
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public String getUserRole(User user) {
        return user.getRole();
    }
}
