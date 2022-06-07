package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);
    int allouer(Long idUser,String name);
   List<Materiel> MesAllocation(Long idUser) ;
   int rendreMateriel(Long idUser,Long idMateriel);
   int ajouterNouveauMateriel(Materiel materiel);
   int supprimerMateriel(Long idMateriel);
   int modifierMateriel(Long idMateriel,String name,String code);
   int indisponibleMateriel(Long idMateriel) ;
   List<Materiel> findAllAlloue();
}
