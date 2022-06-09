package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(int id);
    void ajouterUnMateriel(Materiel materiel);

    void supprimer(int id);

    void modifier(int id, String code,String name);

    void indisponible(int id);

    void allouer(int id, String duree,int id_user, String username);
    void render(int id);
    List<Materiel> listeMaterielAlloue(int user_id);

    List<Materiel> listeAlloue();


}
