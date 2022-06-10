package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {


    void init();

    void listerMateriel();

    void chercherMateriel(Long id);

    void ajouterNouveauMateriel(Materiel materiel);

    void supprimerMateriel(Long id);

    void marquerMaterielIndispo(Long id);

    void modifierMateriel(Long idMateriel, String nouveauText, Long nouveauStock, String codeModification);


    //void modifierMateriel(Materiel materiel);
}
