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
        String usernameUtilisateur = resultSet.getString("UTILISATEUR_USERNAME");
        String dure = resultSet.getString("DUREE");
        boolean disponible= resultSet.getBoolean("DISPONIBLE");
        boolean alloue= resultSet.getBoolean("ALLOUE");
        materiel.setId(id);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setIdUtilisateur(idUtilisateur);
        materiel.setDure(dure);
        materiel.setDisponible(disponible);
        materiel.setAlloue(alloue);
        materiel.setUsernameUtilisateur(usernameUtilisateur);
        
        return materiel;
    }
}
