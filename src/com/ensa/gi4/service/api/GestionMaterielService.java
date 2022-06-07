package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void chercherMateriel(Long id);
    void ajouterNouveauMateriel(Materiel materiel);
    void deleteMateriel(Long id);
    void marquerMaterielIndisponible(Long id);
    void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur);
    void rendreMateriel(Long idMateriel);
    void listeMaterielAlloue(Long id);
    void listeMaterielAlloueAll();
}
