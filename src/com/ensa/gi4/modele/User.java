package com.ensa.gi4.modele;

public class User {

    private Long idUser;
    private String username;
    private String mdp;
    private String role;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     public boolean isUserAdmin(){
        if(this.role.equalsIgnoreCase("admin")){
            return true;
        }else
            return false;
     }

    public String getPassword() {
        return mdp;
    }

    public void setPassword(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
