package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface GestionMaterielService {
    void init();
    void listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerUnMaterial(int id);
    boolean checkAvantSupprimer(int id);
    void modifierUnMateriel(int id, String code,String name);
    void chercherMateriel(int id);
    void materielIndisponible(int id);

    void materialAlloue(int id, String duree,int id_user, String username);

    void renderMateriel(int id);

    Boolean isDispo(int id);

    void AllMaterielAlloue(int user_id);

    void allAllloue();
}
