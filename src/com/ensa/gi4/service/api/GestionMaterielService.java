package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void chercherMateriel(Long id);
 
    void alloue(String name,Long idUser,String date);
    void ListerMesAllocations(User user);
    void rendreMateriel(Long idUser,Long idMateriel);
    void supprimerMateriel(Long idMateriel);
    void modifierMateriel(Long idMateriel,String name,String code);
    void  indisponibleMateriel(Long idMateriel);
    void  listerMaterielAlloue();
    Materiel trouverMateriel(Long id);
    
}
