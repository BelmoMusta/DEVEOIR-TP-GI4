package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void suppMateriel(String id);
    boolean findOne(String code);
    int marquerDispo(boolean dispo,String code);
    int modifierMateriel(String code,String nom);
    int allouerMateriel(int userId,String code);
    int rendreMateriel(int userId,String code);
    List<Materiel> materielAlloués(int userId);
    List<Materiel> toutMateriaux();

}
