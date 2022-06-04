package com.ensa.gi4.datatabase.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
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
	public int ajouter(Materiel materiel) { 
		String query = "INSERT INTO  MATERIEL (NAME, CODE, STOCK, DISPONIBILITE) VALUES (?,?,?,?);";
		return super.ajouter(query, materiel);
	}

	@Override
	public List<Materiel> listMaterialByType(TypeMateriel typeMateriel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMateriel(String code, Integer stock, String ancienCode) {
		String queryString = "UPDATE MATERIEL SET CODE=?, STOCK=? WHERE CODE=?;"; 
		return super.update(queryString,code, stock, ancienCode); 
	}


	@Override
	public int supprimerMateriel(String code) {
		String query = "DELETE MATERIEL WHERE CODE =? ;"; 
		return super.supprimer(query, code); 
	}

	@Override
	public int materielIndisponible(String code) {
		String query1 = "SELECT ID FROM MATERIEL WHERE CODE=?;"; 
		Map<String, Object> idMap =  super.findMaterielId(query1, code); 
		
		// à utiliser pour la condition d'allocation 
		/*String query2 = "SELECT COUNT(*) count FORM ALLOCATION WHERE idMateriel=?;";
		Map<String, Object> nombreMaterielAlloueMap = super.nombreMaterielAlloue(query2,(Integer) idMap.get("ID"));
		
		String query3  ="SELECT STOCK FORM MATERIEL WHERE ID=?"; 
		Map<String, Object> nombreMaterielStockMap =  super.nombreMaterielStock(query3, (Integer) idMap.get("ID")); 
		
		Integer nombreMaterielAlloue = (Integer) nombreMaterielAlloueMap.get("count"); 
		Integer nombreMaterielStock = (Integer) nombreMaterielStockMap.get("Stock");
		
		if (nombreMaterielAlloue == nombreMaterielStock) {
			String query = "UPDATE MATERIEL SET DISPONIBILITE=? WHERE ID=?;"; 
		 return	super.materielIndisponible(query, false, (Integer) idMap.get("Id"));
		}*/
		
		String query = "UPDATE MATERIEL SET DISPONIBILITE=? WHERE ID=?;";
		return	super.materielIndisponible(query, false, (Integer) idMap.get("Id"));
	}


}
