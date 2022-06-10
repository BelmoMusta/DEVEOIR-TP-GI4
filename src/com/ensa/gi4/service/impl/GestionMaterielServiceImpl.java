package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    ApplicationPublisher publisher;

    Materiel materiel;
    String oldCode;
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        List<Materiel> materielList = materielDao.findAll();
        if(materielList.size()!=0)
        {
            System.out.println("La liste des materiels : ");
            for(Materiel materiel:materielList)
            {
                System.out.println("Materiel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilite : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de materiel a afficher");
        }
    }

    @Override
    public void ajouterNouveauMateriel() {
        saisir("ajouter");
        try
        {
            materielDao.ajouterMateriel(materiel);
            publisher.publish(new MyEvent<>(materiel, EventType.ADD));
        }
        catch (Exception e)
        {
            System.out.println("Ce materiel deja existe,il faut changer le code de materiel");
        }
    }

    @Override
    public void chercherMateriel() {
        saisir("chercher");
        try
        {
            Materiel materielChercher = materielDao.chercherMateriel(materiel);
            System.out.println("Le materiel rechercher : ");
            System.out.println("Materiel "+materielChercher.getCode());
            System.out.println("\tNom : "+materielChercher.getName()+  " \n\tType : " + materielChercher.getTypeMateriel()+ " \n\tDisponibilite : "+ materielChercher.isDisponible()+" \n\tAllocation : "+materielChercher.isAllouer());
        }
        catch (Exception e)
        {
            System.out.println("Ce materiel n'existe pas pour le chercher");
        }
    }

    @Override
    public void supprimerMateriel() {
        saisir("supprimer");
        System.out.println(materielDao.supprimerMateriel(materiel)==0?"Ce materiel n'exsite pas pour le supprimer":"Supprimer Done");
    }

    @Override
    public void modifierMateriel() {
        saisir("modifier");
        System.out.println(materielDao.modifierMateriel(materiel,oldCode)==0?"Ce materiel n'exsite pas pour le modifier":"Update Done");
        publisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
    }

    @Override
    public void allouerMateriel(User user) {
        saisir("allouer");
        try
        {
            Materiel materielChercher = materielDao.chercherMateriel(materiel);
            if(materielChercher.isDisponible())
            {
                materielDao.allouerMateriel(materiel,user);
                System.out.println("Ce materiel est allouer avec succes!");
            }
            else
            {
                System.out.println("Ce materiel n'est pas disponible");
            }
        }
        catch (Exception e)
        {
            System.out.println("Ce materiel n'existe pas pour l'allouer");
        }
    }

    @Override
    public void rendreMateriel() {
        saisir("rendre");
        materielDao.rendreMateriel(materiel);
    }

    @Override
    public void listerMaterielAllouerUserId() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("User Id : ");
        int userId = scanner.nextInt();


        List<Materiel> materielListAllouer = materielDao.findAlloueAllUserId(userId);

        if(materielListAllouer.size()!=0)
        {
            System.out.println("La liste des materiels alloues de userId : "+userId);
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Materiel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilite : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de materiel allouer avec user id: "+userId+" a afficher");
        }
    }

    @Override
    public void listerMaterielAllouerYourUserId(User user) {
        List<Materiel> materielListAllouer = materielDao.findAlloueAllUserId(user.getIdUser());

        if(materielListAllouer.size()!=0)
        {
            System.out.println("La liste des materiels alloues par vous");
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Materiel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilite : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de materiel allouer par vous");
        }
    }

    @Override
    public void listerMaterielAllouer() {

        List<Materiel> materielListAllouer = materielDao.findAlloueAll();

        if(materielListAllouer.size()!=0)
        {
            System.out.println("La liste des materiels alloues : ");
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Materiel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilite : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de materiel allouer a afficher");
        }

    }

    public void saisir(String action)
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour "+action+" un Livre entrer : 1, pour "+action+" un Chaise entrer : 2");
        int saisir = scanner.nextInt();

        if(saisir == 1)
        {
            materiel = new Livre();

            System.out.println("name : ");
            String name = scanner.next();
            name += scanner.nextLine();
            materiel.setName(name);


            if(action.equals("modifier"))
            {
                System.out.println("Le code de materiel a modifier : ");
                oldCode = scanner.next();
                System.out.println("Disponible : ");
                System.out.println("1- pour qu'il soit disponible, entrer 1");
                System.out.println("2- pour qu'il soit non disponible, entrer 2");
                String next = scanner.next();
                if ("1".equals(next)) {
                    materiel.setDisponible(true);
                } else if ("2".equals(next)) {
                    materiel.setDisponible(false);
                }
            }
            if(action.equals("allouer"))
            {
                materiel.setDisponible(false);
                materiel.setAllouer(true);
            }
            if(!action.equals("modifier")) {
                System.out.println("code : ");
                String code = scanner.next();
                materiel.setCode(code);
                if(!action.equals("allouer"))
                {
                    materiel.setDisponible(true);
                }
            }
            if(!action.equals("allouer"))
            {
                materiel.setTypeMateriel("Livre");
                materiel.setAllouer(false);
            }
        }
        if(saisir == 2)
        {
            materiel = new Chaise();

            System.out.println("name : ");
            String name = scanner.next();
            name += scanner.nextLine();
            materiel.setName(name);


            if(action.equals("modifier"))
            {
                System.out.println("Le code de materiel a modifier : ");
                oldCode = scanner.next();
                System.out.println("Disponible : ");
                System.out.println("1- pour qu'il soit disponible, entrer 1");
                System.out.println("2- pour qu'il soit non disponible, entrer 2");
                String next = scanner.next();
                if ("1".equals(next)) {
                    materiel.setDisponible(true);
                } else if ("2".equals(next)) {
                    materiel.setDisponible(false);
                }
            }
            if(action.equals("allouer"))
            {
                materiel.setDisponible(false);
                materiel.setAllouer(true);
            }
            if(!action.equals("modifier")) {
                System.out.println("code : ");
                String code = scanner.next();
                materiel.setCode(code);
                if(!action.equals("allouer"))
                {
                    materiel.setDisponible(true);
                }
            }
            if(!action.equals("allouer"))
            {
                materiel.setTypeMateriel("Chaise");
                materiel.setAllouer(false);
            }
        }
    }

}
