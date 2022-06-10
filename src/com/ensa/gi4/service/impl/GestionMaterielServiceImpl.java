package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {

    @Autowired
    ApplicationPublisher publisher;
    @Autowired
    MaterielDaoImpl materielDaoImp;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materielDaoImp.findAll());
    }

    @Override
    public void chercherMateriel(long id) {
        materielDaoImp.findMateriel(id);
    }

    @Override
    public void ajouterMateriel(Materiel materiel) {
        publisher.publish(new MyEvent<>(materiel, EventType.ADD));
        materielDaoImp.addMateriel(materiel);
    }

    @Override
    public void supprimerMateriel(int id) {
        try {
            materielDaoImp.supprimerMateriel(id);
            publisher.publish(new MyEvent<>(materielDaoImp.getMaterielSupprimé(), EventType.REMOVE));
        }catch (Exception e){
            System.out.println("Message publisher non apparu");
        }
    }

    @Override
    public void modifierMateriel(int id) {

        try {
            materielDaoImp.modifierMateriel(id);
            publisher.publish(new MyEvent<>(materielDaoImp.getMaterielModifié(), EventType.UPDATE));
        }catch (Exception e){
            System.out.println("Message publisher non apparu");
        }
    }

    @Override
    public void rendreIndispo(int id) {
        System.out.println(materielDaoImp.rendreIndispo(id));

    }


}
