package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM materiel;");
    }

    @Override
    public void allouerMateriel(Materiel m,User user) {

        super.allouerMateriel("INSERT INTO allocation(code, username) VALUES(?,?)", m.getCode(),user.getUsername());

    }

    @Override
    public Materiel findOne(String code) {
        return super.findOne("SELECT * FROM MATERIEL WHERE code=?;", code);
    }

    @Override
    protected RowMapper<Materiel> getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }


    @Override
    public void AjouterMaterial(Materiel m) {
        super.AddMaterial("INSERT INTO materiel(code, name,disponible) VALUES(?,?,?)", m);
    }

    @Override
    public void ModifierMateriel(String AncienCode,String  NouveauCode) {
        super.UpdateMateriel("UPDATE materiel SET code = ? WHERE code = ?", NouveauCode,AncienCode);

    }

    @Override
    public void SupprimerMateriel(String code) {
        super.RemoveMateriel("DELETE from materiel where code = ?", code);
    }



    @Override
    public void supprimerMaterielAlloue(Materiel materiel) {
        super.supprimerMaterielAlloue("DELETE from allocation where code=?", materiel.getCode());
    }

    @Override
    public void ModifierDisponibilite(Materiel meteriel) {
        super.ModifierDisponibilite("UPDATE materiel SET disponible = ? WHERE code = ?",meteriel.getDisponible(),meteriel.getCode());

    }

    @Override
    public Materiel RechercheMateriel(String code) {
        return super.findOne("SELECT * FROM materiel WHERE code=?;", code);
    }



    @Override
    protected RowMapper<Allocation> getRowMapperAllocation() { // template method design pattern
        return new AlloactionRowMapper();
    }

    @Override
    public List<Allocation> trouverListAllouesParUser(String username) {

        return super.findListAlloueByName("SELECT * FROM allocation WHERE username = ?;", username);
    }

    @Override
    public List<Allocation> trouverListAlloue() {
        return super.findListAlloue("SELECT * FROM allocation;");

    }

    @Override
    public Allocation trouverMaterielAlloue(Materiel materiel) {

        return super.findAllocate("SELECT * FROM ALLOCATION WHERE code=? ;",materiel.getCode());
    }

}




