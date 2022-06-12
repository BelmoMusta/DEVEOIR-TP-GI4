package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();

    void listerMaterielUser();


    void chercherMateriel();
    void ajouterNouveauMateriel();

    void supprimerMateriel();

    void modifierMateriel();

    void marquerIndisponible();

    void allouerMateriel(Long idUser,String nomUser);

    public boolean estAlloue(Long idMateriel);

    void rendreMateriel(Long idUser);

    void afficherListeMaterielsAllouesParuser(Long idUser);

    void afficherListeMaterielsAlloues();


}
