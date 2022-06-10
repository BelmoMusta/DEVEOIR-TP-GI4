package com.ensa.gi4.datatabase.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
	
	@Value("${sql.materiel.findAllMateriel.query}")
	private String findAllMaterielQuery; 
	
	@Value("${sql.materiel.findOneMateriel.query}")
	private String findOneMaterielQuery; 
	
	@Value("${sql.materiel.addMateriel.query}")
	private String addMaterielQuery; 
	
	@Value("${sql.materiel.updateMateriel.query}")
	private String updateMaterielQuery; 
	
	@Value("${sql.materiel.findIdMaterielByCode.query}")
	private String findIdMaterielByCodeQuery; 
	
	@Value("${sql.user.findIdUserByNameRole.query}")
	private String findIdUserByNameRoleQuery; 
	
	@Value("${sql.materiel.deleteMateriel.query}")
	private String deleteMaterielQuery; 
	
	@Value("${sql.materiel.materielIndisponible.query}")
	private String materielIndisponibleQuery; 
	
	@Value("${sql.materiel.findStockDispoById.query}")
	private String findStockDispoByIdQuery; 
	
	@Value("${sql.materiel.listeMaterielAllouer.query}")
	private String listeMaterielAllouerQuery; 
	
	@Value("${sql.allocation.findNbrAllouerMaterielByIdMateriel.query}")
	private String findNbrAllouerMaterielByIdMaterielQuery; 
	
	@Value("${sql.allocation.addAllocation.query}")
	private String addAllocationQuery; 
	
	@Value("${sql.allocation.deleteAllocation.query}")
	private String deleteAllocationQuery; 
	
    @Override
    public Optional<List<Materiel>> findAll() {
        return super.findAll(findAllMaterielQuery);
    }

    @Override
    public Optional<Materiel> findOne(String code, String name) {
        return super.findOne(findOneMaterielQuery, code, name);
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
		return super.ajouter(addMaterielQuery, materiel);
	}

	@Override
	public int updateMateriel(String code, Integer stock, String ancienCode) {
		Optional<Map<String, Object>> checkIdMaterielMap =  super.findIdMateriel(findIdMaterielByCodeQuery, ancienCode); 
		if (checkIdMaterielMap.isPresent()) {
			return super.update(updateMaterielQuery,code, stock, ancienCode); 
		}else {
			return -1; 
		}
		
	}


	@Override
	public int supprimerMateriel(String code) {
		Optional<Map<String, Object>> checkIdMaterielMap =  super.findIdMateriel(findIdMaterielByCodeQuery, code); 
		
		if (checkIdMaterielMap.isPresent()) {
			return super.supprimer(deleteMaterielQuery, code,(Integer) checkIdMaterielMap.get().get("ID")); 
		}else {
			return -1;
		}
		
	}

	@Override
	public int materielIndisponible(String code) {
		Optional<Map<String, Object>> checkIdMaterielMap =  super.findIdMateriel(findIdMaterielByCodeQuery, code); 
		
		
		if (checkIdMaterielMap.isPresent()) {
			return	super.materielIndisponible(materielIndisponibleQuery, false, (Integer) checkIdMaterielMap.get().get("Id"));
		}else {
			return -1; 
		}
		
		
	}
	
	

	@Override
	public int allouerMateriel(String code, String name, User user) {
		
		Optional<Map<String, Object>> checkIdMaterielMap =  super.findIdMateriel(findIdMaterielByCodeQuery, code); 
		
		Optional<Map<String, Object>> checkIdUserMap = super.findUserId(findIdUserByNameRoleQuery, user.getName(), user.getRole().toString()); 
		
		if (  checkIdMaterielMap.isPresent() && checkIdUserMap.isPresent()) {
			
			Optional<List<Map<String, Object>>> checkNombreMaterielStockMap =  super.materielAllouable(findStockDispoByIdQuery, (Integer) checkIdMaterielMap.get().get("ID")); 
			
			Optional<Map<String, Object>> checkNombreMaterielAlloueMap = super.nombreMaterielAlloue(findNbrAllouerMaterielByIdMaterielQuery, (Integer) checkIdMaterielMap.get().get("ID"));
			
			if (checkNombreMaterielAlloueMap.isPresent() && checkNombreMaterielStockMap.isPresent()) {
				
				Long nombreMaterielAlloue =  (Long) checkNombreMaterielAlloueMap.get().get("TOTAL");  
				Boolean materielAllouable = (Boolean) checkNombreMaterielStockMap.get().get(0).get("DISPONIBILITE"); 
				Integer stockMateriel = (Integer) checkNombreMaterielStockMap.get().get(0).get("STOCK"); 
				
				
				if(nombreMaterielAlloue < stockMateriel && materielAllouable ) {
					return super.allouerMateriel(addAllocationQuery, (Integer) checkIdMaterielMap.get().get("ID"), (Integer) checkIdUserMap.get().get("ID"), Timestamp.valueOf(LocalDateTime.now()));
				}else
					return -1;
					
			}else 
				return -1; 		
		}else {
			return -1; 
		}
		 
	}

	@Override
	public int rendreMateriel(String code, String name, User user) {
		
		Optional<Map<String, Object>> checkIdMaterielMap =  super.findIdMateriel(findIdMaterielByCodeQuery, code); 
		 
		Optional<Map<String, Object>> checkIdUserMap = super.findUserId(findIdUserByNameRoleQuery, user.getName(), user.getRole().toString());
		
		if (checkIdMaterielMap.isPresent() && checkIdUserMap.isPresent()) {
			return super.rendreMateriel(deleteAllocationQuery, (Integer) checkIdMaterielMap.get().get("ID"), (Integer) checkIdUserMap.get().get("ID"));
		}else {
			return -1; 
		}
		
		
	}

	@Override
	public Optional<List<Materiel>> listeMaterielAlloue(User user) {
		Optional<Map<String, Object>> checkIdUserMap = super.findUserId(findIdUserByNameRoleQuery, user.getName(), user.getRole().toString());
		
		if (checkIdUserMap.isPresent()) {
			return super.listeMaterielAlloue(listeMaterielAllouerQuery, (Integer) checkIdUserMap.get().get("ID"), getMaterielAllouerRowMapper()); 
		}else {
			return Optional.empty();
		}
		
	}



}
