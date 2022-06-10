package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface AuthentificationService {
    User login(String name,String password) throws NoSuchAlgorithmException;
    List<User> findAll();
    User findOne(Long id);
    boolean signUp(String name,String password) throws NoSuchAlgorithmException;
    String hach(String pwd)throws NoSuchAlgorithmException;
}
