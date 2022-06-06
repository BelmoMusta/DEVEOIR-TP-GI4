package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void chercherMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
}
