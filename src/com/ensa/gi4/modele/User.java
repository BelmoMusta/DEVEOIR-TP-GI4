package com.ensa.gi4.modele;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class User {
    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
