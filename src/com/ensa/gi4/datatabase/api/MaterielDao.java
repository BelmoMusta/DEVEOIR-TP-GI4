package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();
    Materiel findOne(int id);
    void ajouterMateriel(Materiel materiel);
    void supprimerMateriel(int id);
    void modifierMateriel(int id);
    void rendreMateriel(int id);;
    void modifierDisponibilité(int id);
    void modifierAllocation(int id);
    void allouerMateriel(int  idMateriel, int idUser);
    void ajouterMaterielUser(int  idMateriel, int idUser);

   
  
    

}
