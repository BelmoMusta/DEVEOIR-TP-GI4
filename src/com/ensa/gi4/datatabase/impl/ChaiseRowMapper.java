package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Chaise;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChaiseRowMapper implements RowMapper<Chaise> {
    @Override
    public Chaise mapRow(ResultSet resultSet, int i) throws SQLException {
        Chaise chaise = new Chaise();
        int id = resultSet.getInt("id");
        chaise.setId(id);
        chaise.setName(resultSet.getString("name"));
        chaise.setType(resultSet.getString("type"));
        if("true".equalsIgnoreCase(resultSet.getString("allocated"))){
            chaise.setAllocated(true);
        }else
            chaise.setAllocated(false);
        return chaise;
    }
}
