package com.ensa.gi4.modele;

import org.springframework.stereotype.Component;

@Component
public abstract class User {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "\nid= "+ id + ", name = " + name + ", password = " + password +", role = "+role+"\n";
    }
    private  Long id;
    private String name;
    private String password;
    private String role;


}
