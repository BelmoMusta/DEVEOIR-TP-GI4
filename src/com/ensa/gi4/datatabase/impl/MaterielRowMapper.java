package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class MaterielRowMapper implements RowMapper<Materiel> {
    final EntityUtils entityUtils;
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {

        Materiel materiel = new Materiel() {
        };
        entityUtils.populateFromResultSet(materiel, resultSet);
        return materiel;
    }
}
