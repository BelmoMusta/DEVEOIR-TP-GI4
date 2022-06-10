package com.ensa.gi4.modele;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<Role> userRoles;
    private List<RentedMaterial> rentedMaterials;

    public User() {
        this.userRoles = new ArrayList<>();
    }

    public User(Long id, String username, String firstName, String lastName) {
        this.userRoles = new ArrayList<>();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public List<RentedMaterial> getRentedMaterials() {
        return rentedMaterials;
    }

    public void setRentedMaterials(List<RentedMaterial> rentedMaterials) {
        this.rentedMaterials = rentedMaterials;
    }
}
