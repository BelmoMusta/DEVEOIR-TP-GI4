package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };

       
        String name = resultSet.getString("NAME");
        String code = resultSet.getString("CODE");
        Long id_materiel = resultSet.getLong("ID_MATERIEL");
        Long user_id= resultSet.getLong("USER_ID");
        String user_username = resultSet.getString("USER_USERNAME");
        String dure = resultSet.getString("DUREE");
        boolean alloue= resultSet.getBoolean("ALLOUE");
        boolean disponible= resultSet.getBoolean("DISPONIBLE");
      

materiel.setName(name);
materiel.setCode(code);
materiel.setId(id_materiel);
materiel.setId_user(user_id);
materiel.setDuree(dure);
materiel.setDisponible(disponible);
materiel.setAlloue(alloue);
materiel.setUsername_user(user_username);

  
        return materiel;
    }
}






