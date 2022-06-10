package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
    User login(String name, String password);
    void register(String name,String password);
    void allouer(int idUs,String name,int qte,int nb_days);
    void rendre(String name);
}
