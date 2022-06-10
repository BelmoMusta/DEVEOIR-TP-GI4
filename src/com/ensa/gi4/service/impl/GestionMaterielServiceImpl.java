package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GestionMaterielServiceImpl implements GestionMaterielService {

    final MaterielDao materielDao;
   final  Scanner scanner = new Scanner(System.in);
    final LivreDao livreDao;
    final ChaiseDao chaiseDao;


    @Override
    public void listerMateriel() {
        materielDao.findAll().forEach(System.out::println);
    }

    @Override
    public void ajouterLivre(Livre livre) {
        livreDao.add(livre);
    }

    @Override
    public void ajouterChaise(Chaise chaise) {
        chaiseDao.add(chaise);
    }

    @Override
    public void modifierMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if ( materiel == null){
            System.out.println("NOR FOUND");
            return;
        }

        String type = livreDao.getDetails(materiel.getId()).get("type").toString();
        if (type.equals("LIVRE")){
            Livre livre = livreDao.findOne(materiel.getId());
            //TODO : live.setAuthor(scanner.next())
            System.out.printf("entrer le nouveau nom : ");
            String name = scanner.nextLine();
            livre.setName(name);
            livreDao.update(livre);
        }else{
            Chaise chaise = chaiseDao.findOne(materiel.getId());
            //TODO : chaise.setAuthor(scanner.next())
            System.out.printf("entrer le nouveau nom : ");
            String name = scanner.nextLine();
            chaise.setName(name);
            chaiseDao.update(chaise);
        }
    }

    @Override
    public void supprimerMateriel(int id) {
        materielDao.delete(id);
    }

    @Override
    public void allouerMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if ( materiel == null){
            System.out.println("Ce materiel n'existe pas !");
            return;
        }
        if (materiel.getAllocated().equals(materiel.getStock())){
            System.out.println("pas de stock disponible !");
            return;
        }
        this.materielDao.allocate(materiel);
        System.out.println("Allocation avec succes ");

    }

    @Override
    public void desallouerMateriel(int id) {
        Materiel materiel = materielDao.findOne(id);
        if (materiel == null) {
            System.out.println("pas de stock disponible !");
            return;
        }
        if (materiel.getAllocated().equals(0)){
            System.out.println("vous ne possedez aucune maeriel !");
            return;
        }
        this.materielDao.deallocate(materiel);
        System.out.println("dealocation avec succes ");
    }
}
