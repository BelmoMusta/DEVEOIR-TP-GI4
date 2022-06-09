package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    int ajouterMateriel(Materiel materiel);
  int supprimerMateriel(int id);
int modifierNomMateriel(String nom,int id);
int modifierCodeMateriel(String code,int id);
Materiel chercherMateriel(Long id);
int rendreMaterielIndisponible(Long id);
int allouerMateriel(String name,boolean b1,boolean b2,Long id);
int rendreMateriel(boolean b1,boolean b2,Long id);


}
