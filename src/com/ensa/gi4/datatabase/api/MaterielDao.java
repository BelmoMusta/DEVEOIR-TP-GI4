package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(String code);

    void addMaterial(Materiel materiel);
    int deleteMaterial(Materiel materiel);

    int updateMateral(Materiel materiel);

    int allouerMat(Utilisateur utilisateur, Materiel mat, String duree);
    int rendreMat(Utilisateur utilisateur, Materiel mat);
    int updateAvailability(Materiel materiel);
    List<Materiel> trouverMatAlloue(int idUser);

    int countAlloue();

    int countLivres();
    int countChaises();
}
