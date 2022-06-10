package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.modele.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user= new User();

        Long idUser = resultSet.getLong(1);
        String userName = resultSet.getString(2);
        String role = (String) resultSet.getString(4);


        user.setUserId(idUser);
        user.setUserName(userName);
        user.setRole(role);




        return user;

    }



}
