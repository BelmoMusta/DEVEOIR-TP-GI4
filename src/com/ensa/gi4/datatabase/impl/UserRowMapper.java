package com.ensa.gi4.datatabase.impl;
        import com.ensa.gi4.modele.User;
        import org.springframework.jdbc.core.RowMapper;
        import java.sql.ResultSet;
        import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User() { // because it is abstract
        };
        int idPerson = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);

        //String name_ = resultSet.getString("NAME");
        //String code_ = resultSet.getString("CODE");
        user.setIdUser(idPerson);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }
}
