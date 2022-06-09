package com.ensa.gi4.service.impl.Materiel;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
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
    UserDao userDao;

    @Override
    public void init() {

        System.out.println(" service inititialisation ");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materielDao.afficherMateriel());
    }

    @Override
    public void ajouterNouveauMateriel() {
        System.out.println("1- add a book");
        System.out.println(("2- add a chair"));
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if ("1".equals(next)) {
            System.out.println("enter the name of book : ");
            Scanner scanner3 = new Scanner(System.in);
            String nom = scanner3.next();
            System.out.println("enter the code of book : ");
            Scanner scanner1 = new Scanner(System.in);
            String code = scanner1.next();
            Materiel livre = new Livre();
            livre.setName(nom);
            livre.setCode(code);
            materielDao.ajouterNouveauMateriel(livre);
        }
        else if ("2".equals(next)){
            System.out.println("enter the name of chair : ");
            Scanner scanner4 = new Scanner(System.in);
            String nom = scanner4.next();
            System.out.println("enter the code of chair : ");
            Scanner scanner1 = new Scanner(System.in);
            String code = scanner1.next();
            Materiel chaise = new Chaise();
            chaise.setName(nom);
            chaise.setCode(code);
            materielDao.ajouterNouveauMateriel(chaise);
        } else {
            System.out.println("invalid choice");
        }
    }

    @Override
    public void chercherMateriel() {
        System.out.println("enter the id of the material you are looking for");
        Scanner id = new Scanner(System.in);
        Long ida = id.nextLong();
        try
        {
            System.out.println(materielDao.findOne(ida));
        } catch (Exception e) {
            System.out.println("materiel with id "+ ida +" does not exist ");
        }
    }

    @Override
    public void supprimerMateriel() {
        System.out.println("enter the id of the material you want to delete");
        Scanner sc1= new Scanner(System.in);
        Long id = sc1.nextLong();
        materielDao.supprimerMateriel(id);
    }

    @Override
    public void modifierMateriel() {
        System.out.println("enter the id of the material you want to update : ");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        System.out.println(" new code : ");
        String newBookNmae = scanner.next();
        materielDao.modifierMateriel(code,newBookNmae);
    }

    @Override
    public List<Materiel> afficherMateriel() {

        return materielDao.afficherMateriel();
    }

    @Override
    public void marquerIndisponible() {
        System.out.println("enter the id of the material you want to make available or unavailable :");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        System.out.println("enter 0 if you want to make it unavailable, enter 0 if you want to make it available : ");
        Scanner scanner3 = new Scanner(System.in);
        String code0 = scanner3.next();

        if("1".equals(code0)){
            materielDao.marquerIndisponible(1L,id);
        }
        if("0".equals(code0)){
            materielDao.marquerIndisponible(0L,id);
        }
    }

    @Override
    public void allouerMateriel() {
        System.out.println("enter the id of the material you want to allocate : ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        Long allouer=materielDao.findOne(id).getAllouer();

        if(allouer==0){
            System.out.println("Entre your name ");
            Scanner scanner1 = new Scanner(System.in);
            String name =  scanner1.next();

            List<User> list = userDao.allouerMaterielIdUser(name);
            Long id2 = list.get(0).getId();
            //System.out.println(id2);
            materielDao.allouerMateriel(1L, id2 ,id);
        }
        else if(allouer==1){
            System.out.println("this material is already allocated");
        }
    }

    @Override
    public void rendreMateriel() {
        System.out.println("enter the id of the material you want to return : ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        Long allouer=materielDao.findOne(id).getAllouer();
        if(allouer==1L) {
            materielDao.rendreMateriel(0L, "" ,id);
        }
        else if (allouer==0L){
            System.out.println("this material is not allocated");
        }
    }

    @Override
    public  List<Materiel> afficherMaterielLuiMeme() {
        System.out.println("Entre your name ");
        Scanner scanner1 = new Scanner(System.in);
        String name =  scanner1.next();

        List<User> list = userDao.allouerMaterielIdUser(name);
        Long id2 = list.get(0).getId();
        return materielDao.afficherMaterielLuiMeme(id2);
    }

    @Override
    public List<Materiel> afficherMaterielPourChaqueUser() {
        System.out.println("Entrer user name you want to see his materials ");
        Scanner scanner1 = new Scanner(System.in);
        String name =  scanner1.next();

        List<User> list = userDao.allouerMaterielIdUser(name);
        Long id2 = list.get(0).getId();
        return materielDao.afficherMaterielLuiMeme(id2);
    }


}
