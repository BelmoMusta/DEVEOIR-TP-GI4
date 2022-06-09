package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel();
    void chercherMateriel();
    void supprimerMateriel();
    void modifierMateriel();
    List<Materiel> afficherMateriel();
    void marquerIndisponible();
    void allouerMateriel();
    void rendreMateriel();
    List<Materiel> afficherMaterielLuiMeme();
    List<Materiel> afficherMaterielPourChaqueUser();
}
