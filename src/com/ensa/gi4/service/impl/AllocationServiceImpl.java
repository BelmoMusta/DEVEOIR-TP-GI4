package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.AllocationService;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("AllocationService")
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    MaterielDao materielDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    @Qualifier("materielService")
    private GestionMaterielService gestionMaterielService;
    @Override
    public void allouerMateriel(Utilisateur utilisateur, Materiel materiel, String duree) {
        int i = materielDao.allouerMat(utilisateur, materiel, duree);
        int stockLivre = materielDao.countLivres();
        int stockChaise = materielDao.countChaises();
        if(!materiel.getAvailable()){
            System.out.println("ce materiel n'est pas disponible pour le moment");
        } else {
            if(materiel.getType()=="livre" && stockLivre==0){
                System.out.println("Cet allocation n'est pas possible car le materiel de type livre est epuise");
            }else if(materiel.getType()=="chaise" && stockChaise==0){
                System.out.println("Cet allocation n'est pas possible car le materiel de type chaise est epuise");
            } else {

                if(i != 0){

                    gestionMaterielService.modifierAvailable(materiel.getCode(), "non");
                    //System.out.println(materielDao.countAlloue() + "  ");
                    System.out.println("Le materiel " + materiel.getName()+ " a ete alloué avec succes par l'utilisateur: "+ utilisateur.getUsername()+ " pour une duree de " + duree + " jours");
                } else {
                    System.out.println("operation pas reussi, merci de reessayer!!");
                }
            }
        }



    }

    @Override
    public void rendreMateriel(Utilisateur utilisateur, Materiel materiel) {

        int ss = materielDao.rendreMat(utilisateur, materiel);
        if(ss != 0){
            gestionMaterielService.modifierAvailable(materiel.getCode(), "oui");
            System.out.println("Le materiel " + materiel.getName()+ " a ete retourné avec succes par l'utilisateur: "+ utilisateur.getUsername() + "  " + ss);

        } else {
            System.out.println(materielDao.countAlloue());
            System.out.println(utilisateur.getUsername()+ "  " + materiel.getCode());
            System.out.println("operation pas reussi, merci de reessayer!!" );
        }
    }

    @Override
    public void listerMaterielAllou(int idUser) {
        System.out.println(materielDao.trouverMatAlloue(idUser));
    }

    @Override
    public void listerTouTAllocation() {
        List<Utilisateur> utilisateurs= utilisateurDao.findAllUsers();

        for (int i = 0; i< utilisateurs.size(); i++){
            System.out.println("les objets alloué par l'utilisateur: "+ utilisateurs.get(i).getUsername() + " sont: ");

            for(Materiel temp: materielDao.trouverMatAlloue(utilisateurs.get(i).getIdUser())){
                System.out.println(temp);
            }
        }
    }

}
