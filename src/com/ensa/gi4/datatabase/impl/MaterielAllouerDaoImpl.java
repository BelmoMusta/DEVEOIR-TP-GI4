package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.modele.MaterielAllouer;

@Repository
public class MaterielAllouerDaoImpl extends GenericDAO<MaterielAllouer> implements MaterielAllouerDao {

	@Override
	protected MaterielAllouerParUserRowMapper getRowMapper() {
		return new MaterielAllouerParUserRowMapper();
	}
	
	@Override
	public List<MaterielAllouer> listeMaterielAlloueParUser() {
	
		String query = "SELECT USER.name  userName, User.role, MATERIEL.name materielName, MATERIEL.code, Materiel.stock, ALLOCATION.dateAllocation FROM USER, MATERIEL, ALLOCATION WHERE ALLOCATION.idMateriel=Materiel.id AND  ALLOCATION.idUser=User.id \r\n"
			+ "GROUP BY (USER.name, Materiel.name,  MATERIEL.code) ORDER BY USER.id;"; 
		return super.listeMatereilAlloueParListUser(query); 
	}

}
