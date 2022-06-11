package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    Materiel findOne(Long id);
    int ajouterNouveauMateriel(Materiel materiel);
    int supprimerMateriel(Long id);
    int modifierMateriel(String code, String newCode);
    List<Materiel> afficherMateriel();
    int marquerIndisponible(Long dispo ,Long id);
    int allouerMateriel(Long i, Long ii, Long id);
    int rendreMateriel(Long i, String id2, Long id);
   List<Materiel> afficherMaterielLuiMeme(Long id2);
}
