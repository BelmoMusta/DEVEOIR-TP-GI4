package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void listerMateriel();
    void ajouterNouveauMateriel(String[] materielFields);
    void chercherMateriel(String searchInput);
    void modifierMateriel(int id);
    void supprimerMateriel(int id);
    void allouerMateriel(int id);
    void desallouerMateriel(int id);
    void listerMaterielAlloue();
    void listerAllocationHistory(int groupId);
}
