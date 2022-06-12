package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.RentedDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("materielDao")
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {

    @Autowired
    RentedDao rentedDao;
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(Long id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public int createNewMaterial(Materiel materiel) {
        System.out.println("Vous etes sur le point de créer un nouveau matériel");
        String query = "INSERT INTO materiel(name,code,is_rented) VALUES(?,?,?)";
        return this.jdbcTemplate.update(query,materiel.getName(),materiel.getCode(),/*materiel.isRented()*/false);
    }

    @Override
    public int deleteMaterial(Long id) {
    System.out.println("Vous etes sur le point de supprimer le matériel");
    String query = "DELETE FROM materiel WHERE id= ?";
    return this.jdbcTemplate.update(query,id);
    }

    @Override
    public int editMaterial(Long id, String name) {
    return this.jdbcTemplate.update("UPDATE materiel SET name= ? WHERE id=?",name,id);
    }

    @Override
    public Materiel findMaterielByName(String name){
        return this.jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE name=?",getRowMapper(),name);
    }

    @Override
    public void rentMateriel(Materiel materiel, User user) {
        System.out.println("Allocation du matériel en cours ... .");
        Materiel m = this.findMaterielByName(materiel.getName());

        if(m.isRented() ==true){
            System.out.println("Le matériel n'est pas disponible pour le moment !!");
            return ;
        }
        RentedMaterial rentedMaterial = new RentedMaterial();
        rentedMaterial.setIdMateriel(materiel.getId());
        rentedMaterial.setIdUser(user.getIdUser());
        rentedDao.addMateriel(rentedMaterial);
        String query = "UPDATE materiel SET is_rented=true WHERE id=?";
        this.jdbcTemplate.update(query,materiel.getId());

        System.out.println("Le matériel a été bien alloué!!");
    }

    @Override
    public void putBackMateriel(Materiel materiel,User user){
        String query = "UPDATE materiel SET is_rented=false WHERE id=?";
        String query1 = "DELETE FROM rented_materiel WHERE id_user=? AND id_materiel=?";
        int i  = this.jdbcTemplate.update(query1,user.getIdUser(),materiel.getId());
        this.jdbcTemplate.update(query, materiel.getId());
        System.out.println("Le matériel a été rendue avec succés!!");

    }

    @Override
    public void markUnavailable(Materiel materiel) {
        this.jdbcTemplate.update("UPDATE materiel SET is_rented=true WHERE id=?",materiel.getId());
    }


    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
