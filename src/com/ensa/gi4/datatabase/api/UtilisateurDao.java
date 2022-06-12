package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

import java.util.List;

public interface UtilisateurDao {

    List<Utilisateur> findAllUsers();
    Utilisateur findUser(String username);
    int hashPassword(Utilisateur utilisateur);
}
