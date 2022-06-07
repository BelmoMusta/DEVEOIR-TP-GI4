package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }
    @Override
    public List<Materiel> MesAllocation(Long idUser) {
        return super.findAll("SELECT * FROM MATERIEL WHERE alloue="+idUser+";");
    }

    @Override
    public Materiel findOne(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
    @Override
	 public int allouer(Long idUser,String name) {
    	return super.allouer("UPDATE MATERIEL SET alloue=? WHERE name=? and alloue is null and disponible='true' LIMIT 1;",idUser,name);
    		
      }
    @Override
	public int rendreMateriel(Long idUser,Long idMateriel) {
    	return super.rendreMateriel("UPDATE MATERIEL SET alloue=null,dateAllocation=null WHERE alloue=? and id=?;",idUser,idMateriel);
     }
    @Override
     public int ajouterNouveauMateriel(Materiel materiel) {
    	return super. ajouterOne("INSERT INTO MATERIEL (name,code) VALUES (?, ?);",materiel.getName(),materiel.getCode());
    }
    @Override
    public int supprimerMateriel(Long id) {
    	return super.supprimerOne("DELETE FROM MATERIEL WHERE ID=?;", id);
    }
    
    @Override
    public int modifierMateriel(Long id,String name,String code) {
    	return super.modifierOne("UPDATE MATERIEL SET name=?,code=? WHERE id=?;",name,code,id);
    }
    @Override
    public int indisponibleMateriel(Long id) {
    	return super.supprimerOne("UPDATE MATERIEL SET disponible='false' WHERE id=?;", id);
    }
    @Override
	public List<Materiel> findAllAlloue(){
    	 return super.findAll("SELECT * FROM MATERIEL where alloue<>null ;");
    }
    
    
}
