package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Material;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Material materiel);
}
