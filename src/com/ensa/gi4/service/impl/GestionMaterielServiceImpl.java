package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    AllocationDao allocationDao;
    @Autowired
    ApplicationPublisher publisher;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        List<Materiel> listMateriel = materielDao.findAll();
        Iterator iterator = listMateriel.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next()+"\n");
        }
    }
    public void listerMaterielUser(){
        try{
        List<Materiel> listMateriel = materielDao.findAll();
        for(Materiel m:listMateriel){
            System.out.println("Le materiel "+m.getId()+ "\n");
            System.out.println("Nom :"+ m.getName()+ ",Code :"+m.getCode() +",Type :"+m.getType() + ",Disponibilite :" + m.getAvailable());
        }
        }catch(EmptyResultDataAccessException e){
            System.out.println("------------------------------------------------------");

            System.out.println("Materiel non trouvé");
            System.out.println("------------------------------------------------------");

        }
    }
    public void chercherMateriel(){
        System.out.println("Entrer l'id du materiel :");
        Scanner clavier = new Scanner(System.in);
        String id = clavier.next();
        try{
            Materiel m = materielDao.findOne(Long.valueOf(id));
            System.out.println("------------------------------------------------------");
            System.out.println("Le materiel recherche");
            System.out.println("Nom :" + m.getName() + "\n Code: " + m.getCode() + "\n Type: " + m.getType() + "\n Disponibilité: " + m.getAvailable());
            System.out.println("------------------------------------------------------");

        }catch(EmptyResultDataAccessException e){
            System.out.println("------------------------------------------------------");
            System.out.println("Materiel introuvable");
            System.out.println("------------------------------------------------------");

        }
    }

    @Override
    public void ajouterNouveauMateriel() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Voulez vous ajouter un livre ou une chaise : (LI/CH)");
        String code = clavier.next().toUpperCase();
        if(code.equals("LI")){
            System.out.println("Entrer le nom du materiel :");
            String name = clavier.next();
            Materiel m = new Livre();
            m.setName(name);
            m.setCode(code);
            m.setType("Livre");

            materielDao.ajouterMateriel(m);
            System.out.println("------------------------------------------------------");
            publisher.publish(new MyEvent<>(m, EventType.ADD));
            System.out.println("------------------------------------------------------");

        }else{
            System.out.println("Entrer le nom du materiel :");
            String name = clavier.next();
            Materiel m = new Chaise();
            m.setName(name);
            m.setCode(code);
            m.setType("Chaise");

            materielDao.ajouterMateriel(m);

            System.out.println("------------------------------------------------------");
            publisher.publish(new MyEvent<>(m, EventType.ADD));
            System.out.println("------------------------------------------------------");
        }

    }

    @Override
    public void supprimerMateriel() {

        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer l'id du materiel");
        String id = clavier.next();
        try{
            materielDao.supprimerMateriel(Long.valueOf(id));
            System.out.println("------------------------------------------------------");
            System.out.println("materiel supprimé avec succes");
            System.out.println("------------------------------------------------------");

        }catch(EmptyResultDataAccessException e){
            System.out.println("------------------------------------------------------");
            System.out.println("Materiel introuvable");
            System.out.println("------------------------------------------------------");

        }

    }

    @Override
    public void modifierMateriel() {


        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer l'id du materiel");
        String id = clavier.next();

        System.out.println("Entrer le nom du materiel :");
        String name = clavier.next();
        System.out.println("Entrer le code du materiel :(CH/LI)");
        String code = clavier.next().toUpperCase();
        System.out.println("Entrer la disponibilite du materiel :(true ou false)");
        String disponibilite = clavier.next();
        try {
            if(materielDao.findOne(Long.valueOf(id)) != null) {
                if (code.equals("LI")) {
                    Materiel m = new Livre();
                    m.setName(name);
                    m.setCode(code);
                    m.setType("Livre");
                    m.setAvailable(Boolean.valueOf(disponibilite));

                    materielDao.modifierMateriel(Long.valueOf(id), m);
                    System.out.println("------------------------------------------------------");
                    publisher.publish(new MyEvent<>(m, EventType.UPDATE));
                    System.out.println("------------------------------------------------------");

                } else {
                    Materiel m = new Chaise();
                    m.setName(name);
                    m.setCode(code);
                    m.setType("Chaise");
                    m.setAvailable(Boolean.valueOf(disponibilite));

                    materielDao.modifierMateriel(Long.valueOf(id), m);
                    System.out.println("------------------------------------------------------");
                    publisher.publish(new MyEvent<>(m, EventType.UPDATE));
                    System.out.println("------------------------------------------------------");


                }
            }
        }catch(EmptyResultDataAccessException e){
            System.out.println("------------------------------------------------------");
            System.out.println("Materiel introuvable");
            System.out.println("------------------------------------------------------");

        }

    }

    @Override
    public void marquerIndisponible() {

        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer l'id du materiel");
        String id = clavier.next();
        try {
            materielDao.materielIndisponible(Long.valueOf(id));
        }catch(EmptyResultDataAccessException e){
            System.out.println("------------------------------------------------------");
            System.out.println("Materiel introuvable");
            System.out.println("------------------------------------------------------");

        }
    }

    @Override
    public void allouerMateriel(Long idUser,String nomUser) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer l'id du materiel à allouer");
        String idMateriel = clavier.next();
        System.out.println("Entrer la durée d'allouement");
        String duree = clavier.next();

        try{
            if(materielDao.estDispo(Long.valueOf(idMateriel)) != null) {
                Materiel m = materielDao.findOne(Long.valueOf(idMateriel));
                materielDao.allouerMateriel(Long.valueOf(idMateriel),m.getName(), idUser,nomUser,Integer.valueOf(duree));
                materielDao.changerQuantite(m.getQuantite()-1,Long.valueOf(idMateriel));
                materielDao.AllocationMateriel(true,Long.valueOf(idMateriel));
                System.out.println("------------------------------------------------------");
                System.out.println("materiel alloué avec succes");
                System.out.println("------------------------------------------------------");
                if(m.getQuantite()-1 == 0){
                    materielDao.DisponibiliteMateriel(false,Long.valueOf(idMateriel));
                }

            }

        }catch(EmptyResultDataAccessException e) {
            System.out.println("------------------------------------------------------");
            System.out.println("Materiel déja alloué");
            System.out.println("------------------------------------------------------");
        }

    }

    @Override
    public boolean estAlloue(Long idMateriel) {
           if(materielDao.estAllouee(idMateriel) == null){
               return true; //not allocated
           }else{
               return false;//allocated
           }
    }

    @Override
    public void rendreMateriel(Long idUser) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer l'id du materiel à rendre");
        String id = clavier.next();
        try{
            if(materielDao.estAllouee(Long.valueOf(id)) != null){
                System.out.println("------------------------------------------------------");
                System.out.println("Ce materiel n'etait pas alloué");
                System.out.println("------------------------------------------------------");
            }else{
                materielDao.rendreMateriel(Long.valueOf(id),idUser);
                System.out.println("materiel rendu avec succes");

            }
        }catch(EmptyResultDataAccessException e){
            Materiel m = materielDao.findOne(Long.valueOf(id));
            materielDao.rendreMateriel(Long.valueOf(id),idUser);
            materielDao.changerQuantite(m.getQuantite()+1, Long.valueOf(id));
            System.out.println("------------------------------------------------------");
            System.out.println("materiel rendu avec succes");
            System.out.println("------------------------------------------------------");
            if(m.getQuantite() +1 == 5){
                materielDao.AllocationMateriel(false,Long.valueOf(id));
            }

        }


    }

    @Override
    public void afficherListeMaterielsAllouesParuser(Long idUser) {
        List<Allocation> listAllocation;
        listAllocation =allocationDao.findAllPerUser(idUser);
        Iterator iterator = listAllocation.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next()+"\n");
        }
        if(listAllocation.size() == 0){
            System.out.println("------------------------------------------------------");
            System.out.println("Vous n'avez rien alloué");
            System.out.println("------------------------------------------------------");
        }


    }

    @Override
    public void afficherListeMaterielsAlloues() {
        List<Allocation> listAllocation;
        listAllocation = allocationDao.findAll();
        Iterator iterator = listAllocation.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next()+"\n");
        }
        if(listAllocation.size() == 0){
            System.out.println("------------------------------------------------------");
            System.out.println("Aucun materiel n'est alloué");
            System.out.println("------------------------------------------------------");
        }

    }
}
