package com.ensa.gi4.datatabase.impl.Materiel;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {

        Materiel materiel = new Materiel() { // because it is abstract
        };
        String id =  resultSet.getString(1);
        String name = resultSet.getString(2);
        String code = resultSet.getString(3);
        Long dispo = resultSet.getLong(4);
        Long allouer = resultSet.getLong(5);
        String  iduser = resultSet.getString(6);
        materiel.setIduser(id);
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setDispo(dispo);
        materiel.setAllouer(allouer);
        materiel.setIduser(iduser);
        return materiel;
    }
}
