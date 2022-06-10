package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public void ajouterMateriel(Materiel materiel) {
        super.ajouterMateriel("INSERT INTO MATERIEL(name,code,typeMateriel,isDisponible,isAllouer) VALUES (?,?,?,?,?)",materiel);
    }

    @Override
    public Materiel chercherMateriel(Materiel materiel) {
        return super.chercherMateriel("SELECT * FROM MATERIEL WHERE code=? and name = ?",materiel);
    }

    @Override
    public int supprimerMateriel(Materiel materiel) {
        return super.supprimerMateriel("DELETE FROM MATERIEL WHERE code=? and name = ?",materiel);
    }

    @Override
    public int modifierDispoMateriel(Materiel materiel) {
        return super.modifierDispoMateriel("UPDATE MATERIEL set isDisponible = ?,isAllouer = 'true' where code = ? and name = ?",materiel);
    }

    @Override
    public int modifierMateriel(Materiel materiel, String oldCode) {
        return super.modifierMateriel("UPDATE MATERIEL set name=?,typeMateriel=?,isDisponible = ?,isAllouer = ? where code = ?",materiel,oldCode);
    }

    @Override
    public void allouerMateriel(Materiel materiel, User user) {
        modifierDispoMateriel(materiel);
        ajouterMaterielUser(materiel,user);
    }

    @Override
    public void rendreMateriel(Materiel materiel) {
        if(modifierAllouerMateriel(materiel)!=0)
        {
            super.supprimerMaterielUser("DELETE FROM USERMATERIEL WHERE code=?",materiel);
        }
        else{
            System.out.println("Pas de materiel a rendre avec ce code et nom");
        }
    }

    @Override
    public void ajouterMaterielUser(Materiel materiel, User user) {
        super.ajouterMaterielUser("INSERT INTO usermateriel(idUser,code) VALUES (?,?)",materiel,user);

    }

    @Override
    public List<Materiel> findAlloueAllUserId(int userId) {
        return super.findAlloueAllUserId("SELECT * FROM MATERIEL JOIN USERMATERIEL ON MATERIEL.code=USERMATERIEL.code JOIN USER ON USERMATERIEL.idUser=USER.idUser WHERE USER.idUser=?;",userId);
    }

    @Override
    public List<Materiel> findAlloueAll() {
        return super.findAlloueAll("SELECT * FROM MATERIEL JOIN USERMATERIEL ON MATERIEL.code=USERMATERIEL.code JOIN USER ON USERMATERIEL.idUser=USER.idUser;");
    }

    @Override
    public int modifierAllouerMateriel(Materiel materiel) {
        return super.modifierAllouerMateriel("UPDATE MATERIEL set isAllouer = ?,isDisponible = 'true' where code = ? and name = ?",materiel);
    }



    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
