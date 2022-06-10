package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.User;

public interface UserDao {
    User login(String name, String password);
    void register(String name,String password);
    String allouer(int idUs,String name,int qte,int nb_days);
    String rendre(String name);
}
