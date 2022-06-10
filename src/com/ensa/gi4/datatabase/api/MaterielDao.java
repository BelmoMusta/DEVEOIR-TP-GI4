package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    void ajouterMateriel(Materiel materiel);
    Materiel chercherMateriel(Materiel materiel);
    int supprimerMateriel(Materiel materiel);
    int modifierDispoMateriel(Materiel materiel);
    int modifierMateriel(Materiel materiel,String oldCode);
    void allouerMateriel(Materiel materiel, User user);
    void rendreMateriel(Materiel materiel);
        void ajouterMaterielUser(Materiel materiel, User user);
    List<Materiel> findAlloueAllUserId(int userId);
    List<Materiel> findAlloueAll();
    int modifierAllouerMateriel(Materiel materiel);

}
