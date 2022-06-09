package com.ensa.gi4.datatabase.impl.Materiel;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Override
    public List<Materiel> afficherMateriel() {

        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public int marquerIndisponible(Long dispo ,Long id) {
        return super.marquerIndisponible("UPDATE MATERIEL SET dispo =? WHERE id=? ;",dispo,id);
    }

    @Override
    public int allouerMateriel(Long i, Long ii, Long id) {
        return super. allouerMateriel("UPDATE MATERIEL SET allouer =?, iduser=? WHERE id=? ;",i, ii,id);
    }

    @Override
    public int rendreMateriel(Long i, String ii, Long id) {
        return super. rendreMateriel("UPDATE MATERIEL SET allouer =?, iduser=? WHERE id=? ;",i, ii,id);
    }

    @Override
    public List<Materiel> afficherMaterielLuiMeme(Long id2) {
        return super.afficherMaterielLuiMeme("SELECT * FROM MATERIEL WHERE allouer=1 AND iduser=?;", id2);
    }


    @Override
    public Materiel findOne(Long id) {

        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public int ajouterNouveauMateriel(Materiel materiel) {
        return super.ajouterNouveauMateriel("INSERT INTO Materiel(name,code) values(?,?);", materiel.getName(), materiel.getCode());
    }

    @Override
    public int supprimerMateriel(Long id) {
        return super.supprimerMateriel("DELETE FROM MATERIEL WHERE ID=?;",id);
    }

    @Override
    public int modifierMateriel(String code, String newCode) {
        return super.modifierMateriel("UPDATE MATERIEL SET CODE = ? WHERE code=? ;",newCode,code);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
