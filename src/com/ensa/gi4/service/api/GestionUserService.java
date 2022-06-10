package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
    Boolean trouverUser(User user);
    Boolean trouverRole(User user,String role);
    //User findUserInfo(String username,String password);

}
