package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Users;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(String code) {
        return super.findOne("SELECT * FROM MATERIEL WHERE code=?;", code);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
    	
        return new MaterielRowMapper();
    }

	@Override
	public int addOne(Materiel m) {
		// TODO Auto-generated method stub
		return super.addOne("INSERT into MATERIEL (code,name) VALUES (?,?)",m.getCode(),m.getName());
	}

	@Override
	public int deleteOne(String code) {
		// TODO Auto-generated method stub
		return super.deleteOne("delete from MATERIEL where code = ?", code);
	}

	@Override
	public int changeAvailability(boolean dispo,String code) {
		// TODO Auto-generated method stub
		if(dispo == true) {
			return super.checkAvailability("update Materiel set dispo = true where code = ? ",code);
		}else {
			return super.checkAvailability("update Materiel set dispo = false where code = ? ",code);
		}
		
	}
	@Override
	public int updateName(String code,String nom) {
		return super.updateName("update Materiel set name = ? where code = ?",nom,code);
	}

	@Override
	public int allocateMateriel(int userId,String code) {
		// TODO Auto-generated method stub
		return super.allocateMateriel("update Materiel set userId = ? where code = ?",userId, code);
	}

	@Override
	public int returnMateriel(int userId, String code) {
		// TODO Auto-generated method stub
		return super.allocateMateriel("update Materiel set userId = null where userId = ? AND code = ? ", userId, code);
	}

	@Override
	public List<Materiel> findAllocatedMateriel(int userId) {
		// TODO Auto-generated method stub
		return super.findAllocatedMaterielG("SELECT * FROM MATERIEL where userId = ?",userId);
	}



	
}
