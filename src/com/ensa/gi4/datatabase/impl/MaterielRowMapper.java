package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() ;
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        int allocated = resultSet.getInt("allocated");
        int stock = resultSet.getInt("stock");
        String available = resultSet.getString("available");
        if("yes".equalsIgnoreCase(available)) materiel.setAvailable(true);
        else materiel.setAvailable(false);
        materiel.setAllocated(allocated);
        materiel.setId(id);
        materiel.setType(type);
        materiel.setName(name);
        materiel.setStock(stock);

        return materiel;
    }
}
