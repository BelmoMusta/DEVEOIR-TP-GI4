package com.ensa.gi4.database.impl.MaterialTypeDao;

import com.ensa.gi4.database.api.MaterialTypeDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.MaterialType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MaterialTypeDaoImpl extends GenericDAO<MaterialType> implements MaterialTypeDao {

    @Override
    public MaterialType findOne(Long id) {
        String sql = "SELECT * FROM material_type WHERE id=?";
        return super.findOne(sql, id);
    }

    @Override
    public List<MaterialType> findAll() {
        String sql = "SELECT * FROM material_type";
        return super.findAll(sql);
    }

    @Override
    public Long createNewMaterialType(String name) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        simpleJdbcInsert.withTableName("material_type").usingGeneratedKeyColumns("id");
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", name);
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    protected RowMapper<MaterialType> getRowMapper() {
        return new MaterialTypeRowMapper();
    }
}
