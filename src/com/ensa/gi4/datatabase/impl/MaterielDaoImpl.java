package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

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
        String query = "INSERT INTO MATERIEL (NAME, CODE, TYPE , isAvailable) VALUES (?,?,?,?)";
        Object [] args = {materiel.getName(),materiel.getCode(),materiel.getType(), "true"};
        return super.addMateriel(query, args);
    }

    @Override
    public int supprimerMateriel(Long id) {
        String query = "DELETE FROM MATERIEL WHERE id=?";
        return super.deleteMateriel(query, id);
    }

    @Override
    public int materielIndisponible(Long id) {
        String query = "UPDATE MATERIEL SET isAvailable=FALSE WHERE id = ?";
        return super.materielInavailable(query, id);


    }

    @Override
    public int modifierMateriel(Long id, Materiel materiel) {
        String query ="UPDATE MATERIEL SET NAME= ?,CODE= ? ,TYPE= ? ,isAvailable= ? WHERE id=?";
        return super.updateMateriel(query,materiel.getName(),materiel.getCode(), materiel.getType(), materiel.getAvailable(),id);

    }

    @Override
    public int allouerMateriel(Long idMateriel,String nomMateriel, Long idUtilisateur,String nomUser, int dureeAllocation) {
        String query = "INSERT INTO GESTIONALLOCATION (IDMATERIEL,NOMMATERIEL,IDUSER,NOMUSER,DUREE) VALUES (?,?,?,?,?)";
        return super.allocateMateriel(query,idMateriel,nomMateriel,idUtilisateur,nomUser,dureeAllocation);
    }





    @Override
    public Materiel estAllouee(Long idMateriel) {
        String query = "SELECT * FROM MATERIEL WHERE isAllocated= FALSE AND ID=?;";
        return super.findOne(query,idMateriel);

    }

    @Override
    public Materiel estDispo(Long idMateriel) {
        String query = "SELECT * FROM MATERIEL WHERE isAvailable= TRUE AND ID=?;";
        return super.findOne(query,idMateriel);

    }

    public int changerQuantite(int nvQuantite , Long idMateriel){
        String query= "UPDATE MATERIEL SET QUANTITE= ? WHERE ID=?";
        return super.changerQuantite(query,nvQuantite,idMateriel);
    };

    public int DisponibiliteMateriel(boolean disponibilite,Long idMateriel){
         String query="UPDATE MATERIEL SET ISAVAILABLE =? WHERE ID=?";
         return super.DisponibiliteMateriel(query,disponibilite,idMateriel);
    };

    public int AllocationMateriel(boolean disponibilite,Long idMateriel){
        String query="UPDATE MATERIEL SET ISALLOCATED =? WHERE ID=?";
        return super.AllocationMateriel(query,disponibilite,idMateriel);
    };

    @Override
    public int rendreMateriel(Long idMateriel,Long idUser) {
        String query = "DELETE FROM GESTIONALLOCATION WHERE IDMATERIEL=? AND IDUSER=?";
        return super.returnMateriel(query,idMateriel,idUser);

    }



    @Override
    public List<Materiel> materielAlloue(Long idUtilisateur) {
        String query = "SELECT * FROM GESTIONALLOCATION WHERE IDUSER="+idUtilisateur+";";
        return super.findAll(query);
    }

    @Override
    public List<Materiel> listeMaterielAlloueAll() {
        String query = "SELECT * FROM GESTIONALLOCATION;";
        return super.findAll(query);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
