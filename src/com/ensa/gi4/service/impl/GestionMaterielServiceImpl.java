package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.AllocationDAO;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;
    @Autowired
    AllocationDAO allocationDao;
    @Autowired
    UserDAO userdao;
    @Value("${msg.found}")
    private String found;
    @Value("${msg.notfound}")
    private String notfound;
    
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        java.util.List<Materiel> materielList = materielDao.findAll();
        
        System.out.println("La liste des materiels est : ");
        
        for(Materiel m:materielList)
        {
                System.out.println(m.toString());
 
        }
    }

    @Override
    public void ajouterNouveauMateriel() {

        Materiel materiel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour ajouter un Livre entrer: 1\npour ajouter une Chaise entrer: 2");
        int choix = scanner.nextInt();
        
        if(choix == 1) // ajout d'un livre
        {
            materiel = new Livre();

            System.out.println("Donnez le nom du nouveau livre: ");
            String nom = scanner.next();
            System.out.println("Donnez le code du nouveau livre: ");
            String code = scanner.next();
            System.out.println("Donnez la quantite disponible en stocke du nouveau livre: ");
            int qte = scanner.nextInt();
            materiel.setName(nom);
            materiel.setCode(code);
            materiel.setDisponible(true);
            materiel.setQte(qte);
            // fonction dao pour ajouter le materiel a la BD
            materielDao.addOne(materiel); 
        }
        else if(choix == 2)
        {
            materiel = new Chaise();

            System.out.println("Donnez le nom de la nouvelle chaise: ");
            String nom = scanner.next();
            System.out.println("Donnez le code de la nouvelle chaise: ");
            String code = scanner.next();
            System.out.println("Donnez la quantite disponible en stocke de la nouvelle chaise: ");
            int qte = scanner.nextInt();
            materiel.setName(nom);
            materiel.setCode(code);
            materiel.setDisponible(true);
            materiel.setQte(qte);
            materielDao.addOne(materiel);
        }   
    }

  @Override
    public void chercherMateriel() {
          Materiel materiel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Taper le code du materiel que vous cherchez:");
        String code = scanner.next();
             materiel= materielDao.findOne(String.valueOf(code));
             if( materiel != null)
             {
                System.out.println(found+materiel);
             }   
             else
             {
                 System.out.println(notfound);
             }
    }
   
    @Override
    public void supprimerMateriel() {
        Materiel materiel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Taper le code du materiel que vous voulez supprimer:");
        String code = scanner.next();
        materiel=materielDao.findOne(code);
        if(materiel!=null)
        {
            materielDao.removeOne(code);
            System.out.println("Le materiel a été bien supprimer");
        }
        else
            System.out.println("veuillez sasir un code valide");
    }
    
  @Override
    public void modifierMateriel() {
         Materiel materiel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Taper le code du materiel que vous voulez modifier:");
        String code = scanner.next();
        materiel= materielDao.findOne(String.valueOf(code));
             if( materiel != null)
             {
                System.out.println(found+materiel);
                System.out.println("Que voulez vous changer?");
                System.out.println("1- pour modifier le nom du materiel, entrer 1");
                System.out.println("2- pour modifier le code du matériel, entrer 2");
                System.out.println("3- pour modifier la quantité du matériel, entrer 3");
                String next = scanner.next();

                 switch (next)
                {
            
                    case "1": System.out.println("Donnez le nouveau nom:"); next = scanner.next(); materiel.setName(next);break;
                    case "2": System.out.println("Donnez le nouveau code:"); next = scanner.next(); materiel.setCode(next);break;
                    case "3": System.out.println("Donnez la nouvelle quantite:"); next = scanner.next(); materiel.setQte(Integer.valueOf(next)); break;
                    default: System.out.println("choix invalide"); break;
                }
                 materielDao.updateOne(materiel);
                 System.out.println("Materiel bine modifie, les nouveaux infs sont:"+materiel);
             }   
             else
             {
                 System.out.println(notfound);
             }
    
    }

    @Override
    public void allouerMateriel(int idU) {
    
         Scanner scanner = new Scanner(System.in);
         Materiel materiel;
        //demander le code du materiel 
        System.out.println("Veuillez donnez le code du materiel que vous souhaitez allouer: ");
        String code = scanner.next();
        
        //verifier si le materiel choisie existe deja dans BD
        materiel= materielDao.findOne(code);
             if( materiel != null)
             {
                 // si le materiel existe sur La BS, verifier sa disponibilite en STOck
                 //1- recuperer les d'allocations avec le materiel en question
                java.util.List<Allocation> allocs = allocationDao.findSome(materiel.getIdM());
                
                //comparere la somme des qta avec le qte du meteriel pour en deduire la disponibilite 
                int qta=0;
                if(allocs!=null)
                {
                   
                for(Allocation aloc:allocs)
                    qta+=aloc.getQta();
                }
                
                if(qta<materiel.getQte())
                {
                     System.out.println("Le materiel est disponible en stocke");
                     //preparation de la nouvelle allocation
                     Allocation newAlloc = new Allocation();
                     newAlloc.setIdM(materiel.getIdM());
                     System.out.println("Veuillez saisir la quantite que vois voulez allouer (maximum "+(materiel.getQte()-qta)+")");
                     int qtaN = scanner.nextInt();
                     newAlloc.setQta(qtaN);
                     newAlloc.setRendu(false);
                     newAlloc.setIdU(idU);
                     allocationDao.insertAllocation(newAlloc);          
                }
                else{
                    System.out.println("stocke epuisé");
                }

                 
             }
             else
             {
                 System.out.println("Le materiel que vous avez demandez d'allouer n'existe pas dans la BD");
             }        
    }
    @Override
    public void modifierDisponibilitéMateriel() {
        Materiel materiel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Taper le code du materiel que vous voulez marquer indisponible:");
        String code = scanner.next();
        materiel= materielDao.findOne(code);
        if( materiel != null)
             {
                System.out.println(found+materiel);
                if(materiel.getDisponible())
                {
                   materiel.setDisponible(false);
                   System.out.println("Le materiel est maitenant marque indisponible");
                   materielDao.updateOne(materiel);
                }
                else
                {
                    System.out.println("Materiel deja indisponible");
                }
        
             }
             else
             {
                System.out.println(notfound);
             }
    }

    

    @Override
    public void rendreMateriel(int idU) {
         Scanner scanner = new Scanner(System.in);
         Materiel materiel;
        //demander le code du materiel 
        System.out.println("Veuillez donnez le code du materiel que vous souhaitez rendre: ");
        String code = scanner.next();
        
        //verifier si le materiel choisie existe deja dans BD
        materiel= materielDao.findOne(code);
             if( materiel != null)
             {
                 // si le materiel existe sur La BS, verifier si il est allouer par l'utilisateur courant
                 Allocation alloc = allocationDao.estAllouer(materiel.getIdM(), idU);
                if(alloc!=null)
                {
                   //si le materiel est encore allouer par l'utilisateur
                 if(!alloc.getRendu())
                 {
                     
                   alloc.setRendu(true);
                    // modifier l'allocation
                    allocationDao.updateAllocation(alloc);
                    System.out.println("Le materiel a été bien rendu");
                    
                 }
                 
                 else{
                     System.out.println("le materiel est déja rendu");
                 }
                 
                }
                
                else{
                    System.out.println("ce materiel ne fait pas partie de la liste de vos allocations");
                
                }
                 
                
             }
             else
             {
                 System.out.println("Le materiel que vous avez demandez de rendre n'existe pas dans la BD");
             }      
    }

   
    @Override
    public void listerVosMaterielAllouer(int idU) {
       
        java.util.List<Allocation> idMs = allocationDao.getMyAllocations(idU);
        Materiel materiel;
        System.out.println("La liste de vos materiel allouer:");
        for(Allocation alloc:idMs)
        {
            materiel= materielDao.findOneById(alloc.getIdM());
            System.out.println("\t"+materiel);
            
        }
    }

    @Override
    public void listerEmployeMaterielAllouer() {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Veuillez donnez le nom de l'employer pour qui vous voulez afficher la liste:");
       String nom = scanner.next();
       //get userId by name
       User u = userdao.findId(nom);
       if(u!=null)
       {
       //get materiel liste
       java.util.List<Allocation> idMs = allocationDao.getMyAllocations(u.getIdU());
        Materiel materiel;
        System.out.println("La liste des materiels allouer par "+u.getUsername()+" est:");
        for(Allocation alloc:idMs)
        {
            materiel= materielDao.findOneById(alloc.getIdM());
            System.out.println("\t"+materiel);
            
        }
       }
       
       else
            System.out.println("user not found");
    }

    
}
