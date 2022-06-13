package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void chercherMateriel(Long id);
    void ajouterNouveauMateriel(Materiel materiel);
    void deleteMateriel(Long id);
    void marquerMaterielIndisponible(Long id);
    void allouerMateriel(Long idMateriel, String dure, Long idUtilisateur, String usernameUtilisateur);
    void rendreMateriel(Long idMateriel);
    void listeMaterielAlloue(Long id);
    void listeMaterielAlloueAll();
    void modifierMateriel(Long id, String code);
    boolean isDisponible(Long id);
    boolean isExiste(Long id);
    boolean isAlloue(Long id);
    Materiel findMateriel(Long id);
}
