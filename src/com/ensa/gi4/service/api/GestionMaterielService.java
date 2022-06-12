package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

import java.util.ArrayList;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(String materielName, String materielType, String materielCode);

    void supprimerMateriel(String materielName, String materielCode);

    Materiel chercherMateriel(String code);

    void modifierMateriel(String materielCode,String materielName,String materielType,String materielAvailable);

    void modifierAvailable(String materielCode, String materielEtat);
}
