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
    public Materiel findOne(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", (long) id);
    }

    @Override
    public void ajouterUnMateriel(Materiel materiel) {
        super.upd("INSERT INTO MATERIEL (NAME, CODE, TYPE) VALUES ('"+materiel.getName()+"', '"+materiel.getCode()+"','"+materiel.getType()+"' );");

    }

    @Override
    public void supprimer(int id) {
        super.upd("DELETE FROM MATERIEL WHERE ID = "+id);
    }

    @Override
    public void modifier(int id, String code, String name) {
        super.upd("UPDATE MATERIEL SET NAME = '"+name+"' ,CODE = '"+code+"' WHERE ID="+id);
    }

    @Override
    public void indisponible(int id) {
        super.upd("UPDATE MATERIEL SET DISPO = FALSE   WHERE ID="+id);
    }

    @Override
    public void allouer(int id, String duree,int id_user, String username) {
        super.upd("UPDATE MATERIEL SET DUREE = '"+duree+"', ALLOUE = TRUE,  user_id = '"+id_user+"', username = '"+username+"' WHERE ID="+id);
    }

    @Override
    public void render(int id) {
        super.upd("UPDATE MATERIEL SET ALLOUE = FALSE  , USER_ID = NULL, DUREE = NULL, USERNAME = NULL WHERE ID="+id);
    }

    @Override
    public List<Materiel> listeMaterielAlloue(int user_id) {
        return super.findAll("SELECT * FROM MATERIEL WHERE USER_ID="+user_id+";");
    }

    @Override
    public List<Materiel> listeAlloue() {
        return super.findAll("SELECT * FROM MATERIEL WHERE ALLOUE=TRUE;");
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
