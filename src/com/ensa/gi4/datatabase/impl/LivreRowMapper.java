package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreRowMapper implements RowMapper<Livre> {
    @Override
    public Livre mapRow(ResultSet resultSet, int i) throws SQLException {
        Livre livre = new Livre();
        int id = resultSet.getInt("id");
        livre.setId(id);
        livre.setName(resultSet.getString("name"));
        livre.setType(resultSet.getString("type"));
        if("true".equalsIgnoreCase(resultSet.getString("allocated"))){
            livre.setAllocated(true);
        }else
            livre.setAllocated(false);
        return livre;
    }
}
