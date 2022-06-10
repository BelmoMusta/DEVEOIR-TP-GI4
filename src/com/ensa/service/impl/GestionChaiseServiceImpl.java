package com.ensa.service.impl;

import com.ensa.dao.AllocationDao;
import com.ensa.dao.ChaiseDao;

import com.ensa.listeners.ChaiseEvent;
import com.ensa.listeners.LivreEvent;
import com.ensa.modele.*;
import com.ensa.service.api.GestionChaiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("chaise")
public class GestionChaiseServiceImpl implements GestionChaiseService, ApplicationEventPublisherAware {
    ChaiseDao DAO;
    @Autowired
    AllocationDao allodao;
    private ApplicationEventPublisher publisher;

    @Autowired
    public void setDAO(ChaiseDao Daoimpl){
        this.DAO=Daoimpl;
    }
    @Override
    public void init() {
        System.out.println("inititialisation du service des Chaises");
    }

    @Override
    public void listerMateriel() {
        List<Chaise> chaises =DAO.findAll();

        for (Chaise c:chaises){
            System.out.println(c.toString());
        }
    }



    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        DAO.ajouter((Chaise) materiel);
        publisher.publishEvent(new ChaiseEvent(this,"ajout",materiel.getName()));

    }

    public Boolean isordered(String code){return DAO.isordered(code);}

public Boolean disponibilite(String code){if(DAO.findByCode(code)!=null) return true;
        else return false;
        }
    @Override
    public void updateorder(String code ,String a) {
DAO.updateorder(code,a);
    }
    public void updateorderforuser(String code ,User u){
    for(  Allocation a:allodao.findAll())  {
        if (u.getUsername().equals(a.getUserid())&&a.getProductid().equals(code)){
            this.updateorder(code,"false");
           allodao.supprimer(code);
        }
    }

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
DAO.modifier((Chaise)materiel,code);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
