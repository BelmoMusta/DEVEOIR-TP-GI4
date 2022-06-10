package com.ensa.service.impl;

import com.ensa.dao.AllocationDao;

import com.ensa.dao.LivreDao;
import com.ensa.listeners.LivreEvent;
import com.ensa.modele.*;
import com.ensa.service.api.GestionLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("livre")
public class GestionLivreServiceImpl implements GestionLivreService, ApplicationEventPublisherAware {
 LivreDao DAO;
    @Autowired
    AllocationDao allodao;
    private ApplicationEventPublisher publisher;
 @Autowired
 public void setDAO(LivreDao Daoimpl){
     this.DAO=Daoimpl;
 }
    @Override
    public void init() {
        System.out.println("inititialisation du service Livres");
    }

    @Override
    public void listerMateriel() {
        List<Livre> livres =DAO.findAll();

       for (Livre l:livres){
           System.out.println(l.toString());
       }
    }



    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        DAO.ajouter((Livre) materiel);
        publisher.publishEvent(new LivreEvent(this,"ajout",materiel.getName()));

    }

    @Override
    public void ajouterNouveauMateriel(String nom) {

    }

    public Boolean isordered(String code){return DAO.isordered(code);}
    public void updateorderforuser(String code , User u){
        for(  Allocation a:allodao.findAll())  {
            if (u.getUsername().equals(a.getUserid())&&a.getProductid().equals(code)){
                this.updateorder(code,"false");
                allodao.supprimer(code);
            }
        }

    }
    public Boolean disponibilite(String code){if(DAO.findByCode(code)!=null) return true;
    else return false;
    }
    @Override
    public void updateorder(String code ,String a) {
        DAO.updateorder(code,a);
    }

    @Override
    public void allouerMateriel(Allocation allo) {

        allodao.ajouter(allo);


    }

    @Override
    public void supprimerMateriel(String code) {
        DAO.supprimer(code);
    }

    @Override
    public void modifierMateriel(Materiel materiel,String code) {
        DAO.modifier((Livre)materiel,code);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
