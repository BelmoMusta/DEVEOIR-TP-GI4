package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDAO;


import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllocated;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import java.util.Scanner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    @Autowired
    MaterielAllocatedDAO materielAllocatedDao;
    @Autowired
    ApplicationPublisher publisher;
    Materiel materiel;
    String Code ;
    

   
 
    @Override
    public void init() {
        System.out.println("inititialisation du service");
        
    }

    @Override
    public void listerMateriel() {
    	 List<Materiel> materielList = materielDao.findAll();
 
        for(Materiel materiel: materielList)
        {
        	 System.out.println("Materiel "+materiel.getIdMateriel());
             System.out.println("Nom du materiel  : "+materiel.getName()+  " \n Code : " + materiel.getCode() +  " \n Type : " + materiel.getType()+ " \n Disponibilite : "+ materiel.isFree()+" \n Allocation : "+materiel.isAllocate()+"\n") ;  
             System.out.println("------------------------------------------------");
        }
 
    }
    @Override
	public void ajouterNouveauMateriel(Materiel materiel) {
    	 
         materielDao.ajouterMateriel(materiel);
         publisher.publish(new MyEvent<>(materiel, EventType.ADD));
	}

   
	@Override
	public void chercherMateriel(int id) {
		 Materiel materielRecherché = materielDao.findOne(id);
	        if(materielRecherché != null)
	            System.out.println("\n" + materielRecherché );
	        else System.out.println("\n Le matériel que vous cherchez n'existe pas");
		
	}

	@Override
	public void supprimerMateriel(int id) {
		 Materiel materielSupp = materielDao.findOne(id);
	        if(materielSupp != null) {
	            materielDao.supprimerMateriel(id);
	            publisher.publish(new MyEvent<>(materiel, EventType.REMOVE));
	        }else System.out.println("\nCe matériel n'existe pas");
		
	}

	@Override
	public void modifierMateriel(int id ) {
		 Materiel materielModif = materielDao.findOne(id);
	        if(materielModif != null) {
	            materielDao.modifierMateriel(id );
	            publisher.publish(new MyEvent<>(materielModif, EventType.UPDATE));
	        }
	        else System.out.println("\nCe matériel n'existe pas");
		
	}

	@Override
	public void allouerMateriel(int idUser, int idMateriel) {
		Materiel materielSouhaite = materielDao.findOne(idUser);
        if(materielSouhaite.isFree())
        {
            materielDao.allouerMateriel(idUser,idMateriel);
            System.out.println("Ce materiel est allouer avec succes!");
        }
        else
        {
            System.out.println("Ce materiel n'est pas disponible");
        }
		
	}


	@Override
	public void listerMaterielAlloues() {
		materielAllocatedDao.findAlloueAll();
		
	}

	@Override
	public void listerMaterielAllouerChaqueUser() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("User id : ");
        int userId = scanner.nextInt();

        List<MaterielAllocated> listeMaterielAlloue = materielAllocatedDao.findAlloueByUser(userId);

        if(listeMaterielAlloue.size()!=0)
        {
            System.out.println("La liste des materiels alloue a cet utilisateur : "+userId);
            for(MaterielAllocated materiel:listeMaterielAlloue)
            {
                System.out.println("id du materiel "+materiel.getMaterielID());
                System.out.println("\t nom du materiel  : "+materiel.getNameMaterial() );
            }
        }
        else
        {
            System.out.println("Pas de materiel allouer avec user id: "+userId+" a afficher");
        }
		
	}

	@Override
	public void listerMaterielAllouerUser(int id) {
		 System.out.println(materielAllocatedDao.findAlloueByUser(id));
		
	}


	@Override
	public void rendreMateriel(int id) {
		Materiel materiel = materielDao.findOne(id);
        if (materiel.isAllocate()) {
            materielDao.rendreMateriel(id);
            System.out.println("Le matériel est rendu.");
        } else System.out.println(" Echec d'operation.");
		
	}
	public Boolean isFree(int id) {
        Materiel materiel = materielDao.findOne(id);
        if (materiel.isFree()) {
            return true;
        } else return false;
    }


	
	


	
	
}
