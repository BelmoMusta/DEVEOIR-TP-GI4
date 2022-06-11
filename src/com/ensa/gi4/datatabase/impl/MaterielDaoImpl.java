package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.controller.GestionMaterielController;
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
        return super.findOne("SELECT * FROM MATERIEL WHERE id=?;", id);
    }

    @Override
    public int insertMateriel(Materiel materiel) {
        return super.insertMateriel("INSERT INTO  materiel(name, code,dispo,alloué) values (?,?,?,?)", materiel.getName(), materiel.getCode());
    }

    @Override
    public int deleteMateriel(Long id) {
        return super.deleteMateriel("DELETE FROM MATERIEL WHERE ID=?;",id);
    }

    @Override
    public int marquerDisponible(int dispo ,Long id) {
        return super.marquerDisponible("UPDATE MATERIEL SET dispo =? WHERE id=? ;",dispo,id);
    }

    @Override
    public int updateMateriel(String code, Long id) {
            return super.updateMateriel("UPDATE MATERIEL SET CODE = ? WHERE id=? ;",code,id);

    }

    @Override
    public int allouéMateriel(int alloué,Long employee, Long id) {
        return super.allouéMateriel("UPDATE MATERIEL SET alloué = ? , employee= ? WHERE id=? ;",alloué,employee,id);
    }

    @Override
    public int rendreMateriel(int alloué,Long employee, Long id) {
        return super.rendreMateriel("UPDATE MATERIEL SET alloué = ? , employee= ? WHERE id=? ;",alloué,employee,id);
    }

    @Override
    public List<Materiel> ListeParchacun( Long id) {
        return super.ListeParchacun("SELECT * FROM MATERIEL WHERE employee=?;", id);
    }
    @Override
    public List<Materiel> ListeParLui() {
        System.out.println(GestionMaterielController.id);
        return super.ListeParLui("SELECT * FROM MATERIEL WHERE employee=?;", GestionMaterielController.id);
    }


    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
