package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AllocationRowMapper implements RowMapper<Allocation> {
    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation alloc=new Allocation(){};
        Long id =resultSet.getLong(1);
        Long  uid =resultSet.getLong(2);
        Long mid =resultSet.getLong(3);
        Date date=resultSet.getDate(4);
        alloc.setId(id);
        alloc.setUid(uid);
        alloc.setMid(mid);
        alloc.setDate(date);
        return alloc;
    }
}
