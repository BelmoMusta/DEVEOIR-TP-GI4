package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void listerMateriel();
    void ajouterLivre(Livre livre);
    void ajouterChaise(Chaise chaise);
    void modifierMateriel(int id);
    void supprimerMateriel(int id);
    void allouerMateriel(int id);
    void desallouerMateriel(int id);
}
