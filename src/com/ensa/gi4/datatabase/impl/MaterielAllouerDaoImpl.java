package com.ensa.gi4.datatabase.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.modele.MaterielAllouer;

@Repository
public class MaterielAllouerDaoImpl extends GenericDAO<MaterielAllouer> implements MaterielAllouerDao {

	@Value("${sql.materiel.listeMaterielAllouerParUser.query}")
	private String listematerielQuery; 
	
	@Override
	protected MaterielAllouerParUserRowMapper getRowMapper() {
		return new MaterielAllouerParUserRowMapper();
	}
	
	@Override
	public Optional<List<MaterielAllouer>> listeMaterielAlloueParUser() {
		return super.listeMatereilAlloueParListUser(listematerielQuery); 
	}

}
