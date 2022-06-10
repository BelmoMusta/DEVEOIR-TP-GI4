package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {

    @Autowired
    MaterielDao materielDao;

    static int livreCompteur=0;
    static int chaiseCompteur=0;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {

        if(materiel instanceof Livre) {
            materielDao.AjouterMaterial(materiel);
            System.out.println("le "+ materiel.getName()+" " + materiel.getCode() + " est ajoute ! \n");

        }else if(materiel instanceof Chaise) {
            materielDao.AjouterMaterial(materiel);
            System.out.println("la "+ materiel.getName()+" " + materiel.getCode() + " est ajoutee ! \n");
        }
    }

    @Override
    public void allouerMateriel(Materiel materiel,User user) {
        materiel=materielDao.findOne(materiel.getCode());
        if(livreCompteur<5 && materiel.getName().equals("Livre")) {
            if(materiel.getDisponible()==true) {
                materielDao.allouerMateriel(materiel,user);
                livreCompteur++;
                materiel.setDisponible(false);
                materielDao.ModifierDisponibilite(materiel);
                System.out.println("Felicitation, vous avez allouer le livre "+materiel.getCode() +"\n");
            }else {
                System.out.println("le livre est deja alloue !!!!");
            }
        }
        else if(chaiseCompteur<5 && materiel.getName().equals("Chaise")) {
            if(materiel.getDisponible()==true) {
                materielDao.allouerMateriel(materiel,user);
                chaiseCompteur++;
                materiel.setDisponible(false);
                materielDao.ModifierDisponibilite(materiel);
                System.out.println("Felicitation, vous avez allouer la chaise "+materiel.getCode() +"\n");

            }else {
                System.out.println("la chaise est deja allouee !!!");
            }
        }else {
            System.out.println("Vous avez depassez le nombre autorise d'allocation !!");
        }
    }


    @Override
    public void listerMateriels() {

        List<Materiel> liste=materielDao.findAll();

        for(int i=0;i<liste.size();i++) {
            if(liste.get(i).getDisponible()==true) {
                System.out.println("["+liste.get(i).getName()+","+liste.get(i).getCode()+"," + " Disponible" + "]");
            }else {
                System.out.println("["+liste.get(i).getName()+","+liste.get(i).getCode()+","+ " Non Disponible" + "]");
            }
        }
        System.out.println("\n");
    }

    @Override
    public void listerMaterielsAlloues() {

        List<Allocation> liste=materielDao.trouverListAlloue();
        for(int i=0;i<liste.size();i++) {
            System.out.println("["+liste.get(i).getUsername()+","+liste.get(i).getCode()+ "]");
        }
        System.out.println("\n");
    }

    @Override
    public void listerMaterielsAllouesParUser(String username) {

        List<Allocation> list=materielDao.trouverListAllouesParUser(username);

        for(int i=0;i<list.size();i++) {
            System.out.println("["+list.get(i).getUsername()+","+list.get(i).getCode()+ "]");
        }
        System.out.println("\n");
    }

    @Override
    public void modifierMateriel(Materiel materiel, String nouveauCode) {
        materielDao.ModifierMateriel(materiel.getCode(), nouveauCode);
    }

    @Override
    public void rechercheMateriel(Materiel materiel) {
        materiel = materielDao.RechercheMateriel(materiel.getCode());
        String disp=null;
        if(materiel.getDisponible()==true){
            disp="Disponible";
        }else if(materiel.getDisponible()==false){
            disp="Non Disponible";

        }
        System.out.println("["+materiel.getName() + "," + materiel.getCode()+ "," +disp+"]");
    }


    @Override
    public void RendreMateriel(Materiel materiel,String username) {

        materiel=materielDao.findOne(materiel.getCode());
        Allocation allocation=materielDao.trouverMaterielAlloue(materiel);
        if(materiel.getDisponible()==false && allocation.getUsername().equals(username)) {
            materiel.setDisponible(true);
            materielDao.ModifierDisponibilite(materiel);
            materielDao.supprimerMaterielAlloue(materiel);
            System.out.println("le materiel "+ materiel.getCode() + "  a ete rendu avec succes !!");
        }else {
            System.out.println("le materiel n'est pas alloue par vous !!!");
        }

    }

    @Override
    public void supprimerMateriel(Materiel materiel) {
        materielDao.SupprimerMateriel(materiel.getCode());
    }
}
