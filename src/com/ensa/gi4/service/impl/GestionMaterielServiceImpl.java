package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Person;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDaoImpl materielDao;
    @Autowired
    ApplicationPublisher publisher;

    Materiel materiel;
    String oldCode;
    public void saisir(String action)
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour "+action+" un Livre entrer : 1, pour "+action+" une Chaise entrer : 2");
        int saisir = scanner.nextInt();

        if(saisir == 1)
        {
            materiel = new Livre();

            if(!action.equals("modifier"))
            {
                System.out.println("Entrer le nom du livre : ");
                String name = scanner.next();
                name += scanner.nextLine();
                materiel.setName(name);
            }

            if(action.equals("modifier"))
            {
                System.out.println("Le code du matériel à modifier : ");
                oldCode = scanner.next();

                System.out.println("Entrer le nouveau nom du livre : ");
                String name = scanner.next();
                name += scanner.nextLine();
                materiel.setName(name);


                System.out.println("Disponibilité : ");
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
                materiel.setAllouer(false);
            }
            materiel.setTypeMateriel("Livre");
        }
        if(saisir == 2)
        {
            materiel = new Chaise();

            if(!action.equals("modifier"))
            {
                System.out.println("Entrer le nom de la chaise : ");
                String name = scanner.next();
                name += scanner.nextLine();
                materiel.setName(name);
            }

            if(action.equals("modifier"))
            {
                System.out.println("Le code du matériel à modifier : ");
                oldCode = scanner.next();

                System.out.println("Entrer le nouveau nom de la chaise : ");
                String name = scanner.next();
                name += scanner.nextLine();
                materiel.setName(name);


                System.out.println("Disponibilité : ");
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
                materiel.setAllouer(false);
            }
            materiel.setTypeMateriel("Chaise");
        }
    }



    @Override
    public void listerMateriel() {
        List<Materiel> materielList = materielDao.findAll();
        if(materielList.size()!=0)
        {
            System.out.println("La liste des matériels : ");
            for(Materiel materiel:materielList)
            {
                System.out.println("Matériel: "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilité : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de matériel à afficher!");
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
            System.out.println("Ce matériel existe déja,veuillez changer le code du matériel!");
        }
    }

    @Override
    public void chercherMateriel() {
        saisir("chercher");
        try
        {
            Materiel materielChercher = materielDao.chercherMateriel(materiel);
            System.out.println("Le matériel recherché : ");
            System.out.println("Matériel "+materielChercher.getCode());
            System.out.println("\tNom : "+materielChercher.getName()+  " \n\tType : " + materielChercher.getTypeMateriel()+ " \n\tDisponibilité : "+ materielChercher.isDisponible()+" \n\tAllocation : "+materielChercher.isAllouer());
        }
        catch (Exception e)
        {
            System.out.println("Ce matériel n'existe pas!");
        }
    }

    @Override
    public void supprimerMateriel() {
        saisir("supprimer");
        System.out.println(materielDao.supprimerMateriel(materiel)==0?"Ce matériel n'exsite pas!":"Le matériel est supprimé!");
    }

    @Override
    public void modifierMateriel() {

        saisir("modifier");
        int isModified = materielDao.modifierMateriel(materiel,oldCode);

        if(isModified!=0)
        {
            publisher.publish(new MyEvent<>(materiel, EventType.UPDATE));
        }
        else
        {
            System.out.println("Ce matériel n'exsite pas!");
        }
    }

    @Override
    public void allouerMateriel(Person person) {
        saisir("allouer");
        try
        {
            Materiel materielChercher = materielDao.chercherMateriel(materiel);
            if(materielChercher.isDisponible())
            {
                materielDao.allouerMateriel(materiel,person);
                System.out.println("Ce matériel est alloué avec succés!");
            }
            else
            {
                System.out.println("Ce matériel n'est pas disponible.");
            }
        }
        catch (Exception e)
        {
            System.out.println("Ce matériel n'existe pas!");
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
            System.out.println("La liste des matériels alloués de l'utilisateur avec l'ID : "+userId);
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Matériel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilité : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de matériel alloué avec user id: "+userId);
        }
    }

    @Override
    public void listerMaterielAllouerYourUserId(Person person) {
        List<Materiel> materielListAllouer = materielDao.findAlloueAllUserId(person.getIdUser());

        if(materielListAllouer.size()!=0)
        {
            System.out.println("Votre liste des matériels alloués:");
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Matériel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilité : "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de matériel alloué!");
        }
    }

    @Override
    public void listerMaterielAllouer() {

        List<Materiel> materielListAllouer = materielDao.findAlloueAll();

        if(materielListAllouer.size()!=0)
        {
            System.out.println("La liste des matériels alloués : ");
            for(Materiel materiel:materielListAllouer)
            {
                System.out.println("Matériel "+materiel.getCode());
                System.out.println("\tNom : "+materiel.getName()+  " \n\tType : " + materiel.getTypeMateriel()+ " \n\tDisponibilité: "+ materiel.isDisponible()+" \n\tAllocation : "+materiel.isAllouer());
            }
        }
        else
        {
            System.out.println("Pas de matériel alloué!");
        }

    }



}
