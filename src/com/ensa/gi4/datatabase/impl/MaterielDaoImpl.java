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
    public Materiel findOne(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public int ajouterMateriel(Materiel materiel) {
 return super.addMateriel("INSERT INTO MATERIEL (NAME, CODE) VALUES (?,?)",materiel.getName(),materiel.getCode());

    }

    @Override
    public int supprimerMateriel(Long id) {
return  super.deleteMateriel("delete from materiel where id = ?",id);
    }

    @Override
    public int modifierNomMateriel(String nom, Long id) {
        return super.updateNameMateriel("update materiel set name = ? where id = ?",nom,id);
    }

    @Override
    public int modifierCodeMateriel(String code, Long id) {
        return super.updateCodeMateriel("update materiel set code = ? where id = ?",code,id);
    }

    @Override
    public Materiel chercherMateriel(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?",id);
    }

    @Override
    public int rendreMaterielIndisponible(Long id) {
        return super.updateIndisponibleMateriel("update materiel set indisponible = ? where id = ?",true,id);
    }

    @Override
    public int allouerMateriel(String name, boolean b1,boolean b2,Long id) {
        return super.allouerMateriel("update materiel set user = ? , alloue=? , non_alloue=? where id = ?",name,b1,b2,id);
    }

    @Override
    public int rendreMateriel(boolean b1, boolean b2, Long id) {
        return super.rendreMateriel("update materiel set  alloue=? , non_alloue=? where id = ?",b1,b2,id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
