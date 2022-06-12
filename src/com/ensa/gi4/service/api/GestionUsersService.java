package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Users;

public interface GestionUsersService {
    Users verifierUtilsateur(String u,String p);
    List<Users> listUsers();

}
