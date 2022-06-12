package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import org.springframework.stereotype.Repository;

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
    public void addMaterial(Materiel materiel) { super.add("INSERT INTO MATERIEL (name, type, isAvailable, code) VALUES (?,?,?, ?)",materiel);

    }

    @Override
    public int deleteMaterial(Materiel materiel) {
      return super.delete("DELETE FROM MATERIEL WHERE name=? and code=?", materiel);
    }

    @Override
    public int updateMateral(Materiel materiel) {

        return super.update("UPDATE MATERIEL set name=?, type=?, isAvailable = ? where code = ?", materiel);
    }

    @Override
    public int allouerMat(Utilisateur utilisateur, Materiel mat, String duree) {
        return super.allouer("INSERT INTO allocation (idUser, code, temps) VALUES (?, ?, ?) ", utilisateur, mat, duree);
    }

    @Override
    public int rendreMat(Utilisateur utilisateur, Materiel mat) {
        return super.rendre("DELETE FROM allocation where idUser=? and code=?", utilisateur, mat);
    }

    @Override
    public int updateAvailability(Materiel materiel) {
        return super.updateAv("UPDATE MATERIEL set isAvailable = ? where code=?", materiel);
    }

    @Override
    public List<Materiel> trouverMatAlloue(int idUser) {
        return super.matsAlloue("SELECT * FROM materiel JOIN allocation ON MATERIEL.code=allocation.code  WHERE allocation.idUser=?", idUser );
    }
    @Override
    public int countAlloue() {
        return super.countAlloue("SELECT COUNT(*) FROM allocation");
    }

    @Override
    public int countLivres() {
        return super.countLivre("SELECT COUNT(*) FROM materiel where type=? and isAvailable=true", "livre");
    }

    @Override
    public int countChaises() {
        return super.countChaise("SELECT COUNT(*) FROM materiel where type=? and isAvailable=true", "chaise");
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
