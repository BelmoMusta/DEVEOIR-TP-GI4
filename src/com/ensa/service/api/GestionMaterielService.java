package com.ensa.service.api;

import com.ensa.modele.Allocation;
import com.ensa.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void allouerMateriel(Allocation allo);
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerMateriel(String code);
    void modifierMateriel(Materiel materiel,String code);
}
