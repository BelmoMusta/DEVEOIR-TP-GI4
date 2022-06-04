package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void listerMateriel();
    void ajouterNouveauMateriel(String[] materielFields);
}
