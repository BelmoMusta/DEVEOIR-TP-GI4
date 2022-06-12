package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocations;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, int id) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected abstract RowMapper<T> getRowMapper();

   /* protected List<T> findUser(String s, String username, String password) {
        return jdbcTemplate.query(s, getRowMapper(),username, password );
    }*/
   protected T findUser(String query, String username, String password)  {
           return jdbcTemplate.queryForObject(query, getRowMapper(), username,password);
   }

    protected void addMateriel (String query,Materiel materiel){
       jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getAvailability(), materiel.is_available());}

    protected void editMateriel (String query, int id, Materiel materiel){ jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getAvailability(),id);}

    protected void editAvailability (String query,int id,Materiel materiel){ jdbcTemplate.update(query, !materiel.is_available(),id);}

    protected void deleteMateriel (String query,int id){ jdbcTemplate.update(query, id);}

    protected void allocateMateriel(String query, String _query, Materiel materiel, User user, int availability, int allocationQuantity) {
        jdbcTemplate.update(query, materiel.getAvailability() - allocationQuantity, materiel.getId()); // alloationQuantity

           // jdbcTemplate.update(_query, user.getId(), materiel.getId(), allocationQuantity);

         jdbcTemplate.update(_query,availability - allocationQuantity, user.getId(), materiel.getId());

    }

   /* protected void allocateMateriel(Materiel materiel, User user, boolean dispo,int availability, int allocationQuantity) {

       this.jdbcTemplate.queryForObject("SELECT * FROM materiel WHERE id=?",getRowMapper(),materiel.getId());

        if(materiel.is_available()==true && availability>=allocationQuantity){
        String query1 = "INSERT INTO allocations(userId,materielId,availability) VALUES(?,?,?)";
        this.jdbcTemplate.update(query1,user.getId(),materiel.getId(), availability-allocationQuantity);
        System.out.println("Materiel alloué !");
        }
        else System.out.println("Le materiel avec la quantité que vous desirez est indisponible ...");
    }*/


    protected void returnMaterial(String query1, String query2, Materiel materiel, Allocations materielAllocated, int allocationQuantity){
        jdbcTemplate.update(query1, materiel.getAvailability() + allocationQuantity, materiel.getId());
        jdbcTemplate.update(query2,materielAllocated.getAvailability() - allocationQuantity, materielAllocated.getId());
        System.out.println("Le matériel a été rendue avec succés!!");
    }

    protected List<T> findAllMaterielAllocated(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected List<T> findMaterielAllocatedByUser(String query, int userID) {
        return jdbcTemplate.query(query, getRowMapper(), userID);
    }

    protected T findMaterielAllocatedByUserIDAndMaterielID(String query, int userID, int materielID) {

            return jdbcTemplate.queryForObject(query, getRowMapper(), userID, materielID);
    }

    protected T findMaterielAllocatedByMaterielID(String query, int materielId) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), materielId);
    }
}
