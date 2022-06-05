package com.ensa.gi4.datatabase.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
    	String query = "SELECT * FROM MATERIEL;"; 
        return super.findAll(query);
    }

    @Override
    public Materiel findOne(String code, String name) {
    	String query = "SELECT * FROM MATERIEL WHERE CODE=? AND NAME=?;"; 
        return super.findOne(query, code, name);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
    
    protected MaterielAllouerRowMapper getMaterielAllouerRowMapper() {
		return new MaterielAllouerRowMapper(); 
	}

	@Override
	public int ajouter(Materiel materiel) { 
		String query = "INSERT INTO  MATERIEL (NAME, CODE, STOCK, DISPONIBILITE) VALUES (?,?,?,?);";
		return super.ajouter(query, materiel);
	}

	@Override
	public int updateMateriel(String code, Integer stock, String ancienCode) {
		String queryString = "UPDATE MATERIEL SET CODE=?, STOCK=? WHERE CODE=?;"; 
		return super.update(queryString,code, stock, ancienCode); 
	}


	@Override
	public int supprimerMateriel(String code) {
		String query1 = "SELECT ID FROM MATERIEL WHERE CODE=?;"; 
		Map<String, Object> idMaterielMap =  super.findMaterielId(query1, code); 
		String query = "DELETE MATERIEL WHERE CODE =? AND  (NOT  EXISTS  ( SELECT id FROM ALLOCATION WHERE idMateriel =?));"; 
		return super.supprimer(query, code,(Integer) idMaterielMap.get("ID")); 
	}

	@Override
	public int materielIndisponible(String code) {
		String query1 = "SELECT ID FROM MATERIEL WHERE CODE=?;"; 
		Map<String, Object> idMap =  super.findMaterielId(query1, code); 
		
		String query = "UPDATE MATERIEL SET DISPONIBILITE=? WHERE ID=?;";
		return	super.materielIndisponible(query, false, (Integer) idMap.get("Id"));
	}

	@Override
	public int allouerMateriel(String code, String name, User user) {
		// recherche idMateriel
		String query1 = "SELECT ID FROM MATERIEL WHERE CODE=?;"; 
		Map<String, Object> idMaterielMap =  super.findMaterielId(query1, code); 
		
		// recherche idUser
		String query2 = "SELECT ID FROM USER WHERE NAME=? AND ROLE=?;"; 
		Map<String, Object> idUserMap = super.findUserId(query2, user.getName(), user.getRole().toString()); 
		
		// recherche stock et disponiblite du materiel
		String query3  ="SELECT STOCK, DISPONIBILITE FROM MATERIEL WHERE ID=?"; 
		List<Map<String, Object>> nombreMaterielStockMap =  super.materielAllouable(query3, (Integer) idMaterielMap.get("ID")); 
		
		Boolean materielAllouable = (Boolean) nombreMaterielStockMap.get(0).get("DISPONIBILITE"); 
		Integer stockMateriel = (Integer) nombreMaterielStockMap.get(0).get("STOCK"); 
		
		// recherche nombre d'allocation du materiel
		String query4 = "SELECT COUNT(*) as TOTAL FROM ALLOCATION WHERE idMateriel=? ;";
		Map<String, Object> nombreMaterielAlloueMap = super.nombreMaterielAlloue(query4, (Integer) idMaterielMap.get("ID"));
		Long nombreMaterielAlloue =  (Long) nombreMaterielAlloueMap.get("TOTAL"); 

		// allocation du materiel
		String query5 = "INSERT INTO ALLOCATION (idMateriel, idUser, dateAllocation) VALUES (?,?,?); "; 
		
		if(nombreMaterielAlloue < stockMateriel && materielAllouable) {
			return super.allouerMateriel(query5, (Integer) idMaterielMap.get("ID"), (Integer) idUserMap.get("ID"), Timestamp.valueOf(LocalDateTime.now()));
		}else
			return 0;
		 
	}

	@Override
	public int rendreMateriel(String code, String name, User user) {
		// recherche idMateriel
		String query1 = "SELECT ID FROM MATERIEL WHERE CODE=?;"; 
		Map<String, Object> idMaterielMap =  super.findMaterielId(query1, code); 
		
		// recherche idUser
		String query2 = "SELECT ID FROM USER WHERE NAME=? AND ROLE=?;"; 
		Map<String, Object> idUserMap = super.findUserId(query2, user.getName(), user.getRole().toString());
		
		// suppression allocation
		String  query3 = "DELETE ALLOCATION WHERE idMateriel=? AND idUser=?;"; 
		return super.rendreMateriel(query3, (Integer) idMaterielMap.get("ID"), (Integer) idUserMap.get("ID"));
	}

	@Override
	public List<Materiel> listeMaterielAlloue(User user) {
		String query1 = "SELECT ID FROM USER WHERE NAME=? AND ROLE=?;"; 
		Map<String, Object> idUserMap = super.findUserId(query1, user.getName(), user.getRole().toString());
		
		String query3 = "SELECT MATERIEL.name, MATERIEL.code, MATERIEL.stock, ALLOCATION.dateAllocation FROM MATERIEL, USER, AllOCATION WHERE (ALLOCATION.idMateriel=MATERIEL.id AND ALLOCATION.idUser=? )GROUP BY MATERIEL.code;"; 
		return super.listeMaterielAlloue(query3, (Integer) idUserMap.get("ID"), getMaterielAllouerRowMapper()); 
	}



}
