package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.security.SessionHolder;
import enums.LoginResponse;
import com.ensa.gi4.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final SessionHolder sessionHolder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserDao userDao, SessionHolder sessionHolder) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.sessionHolder = sessionHolder;
    }

    @Override
    public User addUser(String username, String email, String password) {
        User user = new User(username, email, passwordEncoder.encode(password));
        return userDao.add(user);

    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        User user =  this.userDao.findOneByUsername(username);
        if (user == null) return LoginResponse.USER_NOT_FOUND;

        if (passwordEncoder.matches(password, user.getPassword()) && !user.isLocked()){
            this.sessionHolder.setLoggedUser(user);
            this.sessionHolder.setValidity(LocalDateTime.now());
            //TODO : Cookie Handling
            return LoginResponse.SUCCESS;
        } else if (passwordEncoder.matches(password, user.getPassword()) && user.isLocked()){
            return LoginResponse.ACCOUNT_LOCKED;
        } else{
            return LoginResponse.PASSWORD_INCORRECT;
        }
    }

    @Override
    public void deleteUser(String username) {
        userDao.delete(username);
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public boolean isUserExpired() {
        return this.sessionHolder.isUserExpired();
    }

    @Override
    public User getLoggedUser(){
        return this.sessionHolder.getLoggedUser();
    }
}