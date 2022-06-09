package com.ensa.gi4.datatabase.impl.User;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.GenericDAO;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao {

    @Override
    public List<User> findAll() {
        return super.findAll("SELECT * FROM USERS;");
    }

    @Override
    public List<User> login(User user) {
        List<User> user1 =  super.login("SELECT * FROM USERS where username=? AND password=?",user.getUserName(),user.getPassword());
        return user1;
    }

    @Override
    protected RowMapper<User> getRowMapper() {

        return new UserRowMapper();
    }

    @Override
    public List<User> allouerMaterielIdUser(String name) {
        return  super.allouerMaterielIdUser("SELECT * FROM USERS where username=?;",name);

    }
}
