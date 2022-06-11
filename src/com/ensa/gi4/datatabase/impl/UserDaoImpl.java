package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.appuser.AppUser;
import com.ensa.gi4.datatabase.api.UserDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDAO<AppUser>  implements UserDao {

    @Override
    public AppUser findOne(Long id) {
        return super.findOne("SELECT * FROM userapp WHERE id=?;", id);
    }

    @Override
    public List<AppUser> loginUser(AppUser user) {
        List<AppUser> usera =  super.loginUser("SELECT * FROM userapp where username=? AND password=?",user.getUserName(),user.getPassword());
     return usera;
    }


    @Override
    protected RowMapper<AppUser> getRowMapper() {
        return new UserRowMapper();
    }
}
