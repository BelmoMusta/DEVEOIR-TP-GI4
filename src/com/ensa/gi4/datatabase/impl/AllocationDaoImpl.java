package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.AllocationDao;
import com.ensa.gi4.modele.Allocation;

@Repository
public class AllocationDaoImpl  extends GenericDAO<Allocation> implements AllocationDao {

	@Override
	protected RowMapper<Allocation> getRowMapper() {
        return new AllocationRowMapper();
	}
	
	@Override
	public List<Allocation> listeMaterielAlloue(int id) {
		return super.findAll("SELECT ALLOCATION.ID, USERNAME, ROLE, NAME, TYPE, DATEDEBUT, DATEFIN FROM MATERIEL, USER, ALLOCATION WHERE USER.ID = ALLOCATION.IDUSER and MATERIEL.ID = ALLOCATION.IDMATERIEL and IDUSER='"+id+"';");
	}

	@Override
	public List<Allocation> listeMaterielAlloueAll() {
		return super.findAll("SELECT ALLOCATION.ID, USERNAME, ROLE, NAME, TYPE, DATEDEBUT, DATEFIN FROM MATERIEL, USER, ALLOCATION WHERE USER.ID = ALLOCATION.IDUSER and MATERIEL.ID = ALLOCATION.IDMATERIEL group by IDUSER,ALLOCATION.ID ;");
	
	}
}
