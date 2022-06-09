package com.ensa.gi4.service.api;

import java.text.ParseException;

import com.ensa.gi4.modele.Materiel;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void chercherMateriel(int ID);
    void modifierMateriel(int ID, String name, String type);
	void supprimerMateriel(int ID);
    void marquerMaterielIndisponible(int ID);
	void allouerMateriel(int idMateriel, String dateDebut, String dateFin, int idUtilisateur);
    void rendreMateriel(int ID);
    void listeMaterielAlloue(int id);
    void listeMaterielAlloueAll();
}
