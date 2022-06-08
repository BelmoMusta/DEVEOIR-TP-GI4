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
    public int aadd(Materiel materiel) {
        return super.add("INSERT INTO MATERIEL (NAME,CODE) VALUES (?, ?)",materiel.getName(),materiel.getCode());
    }

    @Override
    public int deleteMateriel(String code) {
        return super.delet("DELETE FROM MATERIEL WHERE code=?;",code);
    }


    @Override
    public int updateMateril(String code, String newCode) {
        return super.update("UPDATE MATERIEL SET CODE = ? WHERE code=? ;",newCode,code);
    }

    @Override
    public int allouerMateriel(int alloue, Long idUser, Long idMat) {
        return super.allouer("UPDATE chaise SET alloué=?,user=? WHERE id=?  ;",alloue, idUser,idMat);

    }



    @Override
    public int marquerDisponible(int dispo, Long id) {
        return super.marquerDisponible("UPDATE MATERIEL SET dispo =? WHERE id=? ;",dispo,id);
    }

    @Override
    public int renderMaterial(int alloue, Long idUser, Long idMat) {
        return super.allouer("UPDATE chaise SET alloué=?,user=? WHERE id=?  ;",alloue, idUser,idMat);
    }


    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
