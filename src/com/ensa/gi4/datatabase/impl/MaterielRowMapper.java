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
        Long id = resultSet.getLong("ID_MATERIEL");
   //     String name = resultSet.getString(2);
        String name = resultSet.getString("NAME");
        String code = resultSet.getString("CODE");
    //    String code = resultSet.getString(3);
        Long idUtilisateur= resultSet.getLong("UTILISATEUR_ID");
        String dure = resultSet.getString("DUREE");
        materiel.setId(id);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setIdUtilisateur(idUtilisateur);
        materiel.setDure(dure);
        

        return materiel;
    }
}
