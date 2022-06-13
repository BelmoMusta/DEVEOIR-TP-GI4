package com.ensa.gi4.service.impl;

import com.ensa.gi4.Session;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    final UserDao userDao;
    final Session session;
    final BCryptPasswordEncoder passwordEncoder;

    public void loginUser(String username, String password){
        User user = userDao.findOneByUsername(username);
        if(user == null) return;
        if (passwordEncoder.matches(password, user.getPassword())){
            session.setLoggedUser(user);
        }
    }


    public boolean isLogged(){
        return session.isLogged();
    }

    public User getCurrentUser(){
        return session.getLoggedUser();
    }

    public boolean isAdmin(){
        return this.session.getLoggedUser().getRoles().stream().anyMatch(s -> s.getName().equals("ADMIN"));
    }
}
