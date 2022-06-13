/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper<User>{

     @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User() { // because it is abstract
        };

        int idU = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        int id = resultSet.getInt(4);

        user.setIdU(idU);
        user.setUsername(username);
        user.setPassword(password);
        user.setIdR(id);
        return user;
    }
    
}
