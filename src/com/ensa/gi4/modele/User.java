package com.ensa.gi4.modele;

public class User {
    private String name;
    private String role;
private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
