package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface GestionMaterielService {
    void init();
    List<Materiel> listerMateriel();
    void ajouterNouveauMateriel(Materiel materiel);
    void supprimerMateriel(Long id);
    void modifierNomMateriel(String name,Long id) ;
    void modifierCodeMateriel(String code,Long id) ;
   Materiel chercherMateriel(Long id);
   void rendreMaterielIndisponible(Long id);

     void allouerMateriel(String name,boolean b1,boolean b2,Long id);
     void rendreMateriel(boolean b1,boolean b2,Long id );


}
