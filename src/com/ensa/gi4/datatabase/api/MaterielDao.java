package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface MaterielDao {
    List<Materiel> findAll();

    Materiel findOne(String code);
    // compléter par les autres méthodes
    int addOne(Materiel m);
	int deleteOne(String code);
	int changeAvailability(boolean dispo,String code);
	int updateName(String code,String name);
	int allocateMateriel(int userId,String code);
	int returnMateriel(int userId,String code);
	List<Materiel> findAllocatedMateriel(int userId);
    
}
