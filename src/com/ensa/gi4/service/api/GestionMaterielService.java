package com.ensa.gi4.service.api;

public interface GestionMaterielService {

    void listerMateriel();
    void chercherMateriel();
    void ajouterNouveauMateriel();
    void deleteMateriel();
    void updateMateriel();
    void marquerIndispoouDispo();
    void allouerMateriel();
    void rendreMateriel();
    void rendreMaterielParLui();
    void allouerMaterielParLui();
    void ListeParchacun();
    void ListeParLui();
}
