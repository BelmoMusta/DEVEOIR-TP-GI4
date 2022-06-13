package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MaterielDao {	
	List<Materiel> findByName(String name);
	int allouer(int userId, String name);
	int rendreMateriel(int userId, String name);
	List<Materiel> findAlloueByMe(int id);
	List<Materiel> findAll();
	int insert(String name,String type);
	int update(String name,String type,String newName);
	int deleteByName(String name);
	int MarqueIndisponible(String name);
	List<Materiel> findAlloueByUser(String name);
	boolean checkdispo(String next);
	boolean isExistName(String name);
	
	
}
