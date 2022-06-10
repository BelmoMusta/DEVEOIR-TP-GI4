package com.ensa.service.api;

import com.ensa.modele.User;

public interface GestionLivreService extends GestionMaterielService{
    void ajouterNouveauMateriel(String nom);
    public Boolean isordered(String c);
    public void updateorder(String code,String a);
    public Boolean disponibilite(String code);
    public void updateorderforuser(String code, User u);
}
