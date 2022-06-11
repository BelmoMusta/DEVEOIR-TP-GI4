package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(Long id);


    int ajouterMateriel(Materiel materiel);
    int supprimerMateriel(Long id);
    int materielIndisponible(Long id);

    int allouerMateriel(Long idMateriel,String nomMateriel, Long idUtilisateur,String nomUser, int dureeAllocation);

    Materiel estAllouee(Long idMateriel);

    Materiel estDispo(Long idMateriel);

    int changerQuantite(int nvQuantite , Long idMateriel);

    int DisponibiliteMateriel(boolean disponibilite,Long idMateriel);

    int AllocationMateriel(boolean disponibilite,Long idMateriel);

    int rendreMateriel(Long idMateriel, Long idUser);
    int modifierMateriel(Long id, Materiel materiel);
    List<Materiel> materielAlloue(Long idUtilisateur);
    List<Materiel> listeMaterielAlloueAll();
}
