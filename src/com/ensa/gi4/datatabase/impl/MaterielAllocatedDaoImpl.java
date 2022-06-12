package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDAO;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
import com.ensa.gi4.modele.User;
@Repository
public class MaterielAllocatedDaoImpl extends GenericDAO<MaterielAllocated>  implements MaterielAllocatedDAO {
	@Override
	protected RowMapper<MaterielAllocated> getRowMapper() {
		 return new MaterielAllocatedRowMapper();
	
	}

	@Override
	public List<MaterielAllocated> findAlloueByUser(int userId) {
		return super.findAlloueByUserId("SELECT * FROM user_allocate_material WHERE idUser = ?",userId);
		}

	@Override
	public List<MaterielAllocated> findAlloueAll() {
		return super.findAllAlloue("SELECT * FROM user_allocate_material");
	}

	@Override
	public MaterielAllocated findMaterielUser(int userId, int materielId) {
		 return super.findMaterielAllocatedByUser("SELECT * FROM user_allocate_material WHERE userId = ? AND idUserMateriel = ?;", userId, materielId);
		
		
	}


	
	
	

	
	




}