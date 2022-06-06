package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAllMateriel();

    Materiel findOneMateriel(Long id);
   Materiel findWithCode(String code);
   Boolean isDispo(String code);
   int quantiteMateriel(String code);
   void diminuerQuantite(String Code);
	

}
