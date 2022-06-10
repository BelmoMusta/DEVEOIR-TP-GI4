package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Person;
import com.ensa.gi4.modele.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person() { // because it is abstract
        };
        int idPerson = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);

        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        person.setIdUser(idPerson);
        person.setUserName(userName);
        person.setPassword(password);
        person.setRole(role);

        return person;
    }
}
