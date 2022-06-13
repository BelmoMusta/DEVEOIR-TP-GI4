package com.ensa.gi4.datatabase.api;
import java.util.List;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;

public interface UserDao {

    public List<User> getUsers();
    public User getUser( long userId);
    public User Login(String name, String password);}
