package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.RentedDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.RentedMaterial;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Repository
public class RentedDaoImpl extends GenericDAO<RentedMaterial> implements RentedDao {


    @Override
    public List<RentedMaterial> viewRentedMaterialsByEachUser(User user) {
        String query= "SELECT * FROM rented_materiel WHERE id_user=?";
        return this.jdbcTemplate.query(query,new Object[]{user.getIdUser()},getRowMapper());
    }

    @Override
    public List<RentedMaterial> viewRentedMaterialsOfAllUsers() {
        String query = "SELECT * FROM rented_materiel";
        return super.findAll(query);
    }

    @Override
    protected RowMapper<RentedMaterial> getRowMapper() {
        return new RentedMaterialRowMapper();
    }
    @Override
    public int checkNumberRentedMaterial(User user){
        String query2 = "SELECT COUNT(*) FROM rented_materiel WHERE id_user=?";
        //queryForInt() is deprecated
        return this.jdbcTemplate.queryForObject(query2,new Object[] { user.getIdUser() },Integer.class);
    }

    @Override
    public int addMateriel(RentedMaterial rentedMaterial) {
        String query1 = "INSERT INTO rented_materiel VALUES(?,?)";
        Long idUSer =  rentedMaterial.getIdUser();
        Long idMateriel = rentedMaterial.getIdMateriel();
       return this.jdbcTemplate.update("INSERT INTO rented_materiel(id_user,id_materiel) VALUES(?,?)",idUSer,idMateriel);
    }

}
