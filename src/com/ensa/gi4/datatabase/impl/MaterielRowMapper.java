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


        /*Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        String code = resultSet.getString(3);*/




        Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String code = resultSet.getString(3);
        String type = resultSet.getString(4);
        Long stock = resultSet.getLong(5);
        Boolean dispo = resultSet.getBoolean(6);
        System.out.println(dispo);

        materiel.setId(id);
        materiel.setName(name);
        materiel.setCode(code);
        materiel.setType(type);
        materiel.setStock(stock);
        materiel.setDispo(dispo);






        return materiel;
    }
}
