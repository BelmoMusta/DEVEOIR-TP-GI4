package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielAllocatedDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    MaterielAllocatedDao materielAllocatedDao;

    @Autowired
    ApplicationPublisher applicationPublisher;

    @Override
    public void listerMateriel() {
        List<Materiel> materiels = materielDao.getAllMateriels();
        if (materiels.size() == 0) {
            System.out.println("\nLa liste des materiels est vide");
        } else {
            for (Materiel materiel : materiels) {
                System.out.println(materiel);
            }
        }
    }

    @Override
    public void listerAllocations() {
        List<Allocation> allocations = materielAllocatedDao.getAllAllocations();
        if (allocations.size() == 0) {
            System.out.println("\nLa liste des allocations est vide");
        } else {
            for (Allocation allocation : allocations) {
                System.out.println(allocation);
            }
        }
    }

    @Override
    public void listerAllocationsParUser(int userID)  {
        List<Allocation> allocations = materielAllocatedDao.getAllocationsByUser(userID);
        if (allocations.size() == 0) {
            System.out.println("\nVous n'avez aucune allocation en cours");
        } else {
            for (Allocation allocation : allocations) {
                System.out.println(allocation);
            }
        }
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
        Scanner scanner = new Scanner(System.in);
        String nom = "";
        String code = "";
        int quantity = 0;
        boolean availability = true;

        System.out.print("\nNom : ");
        nom = scanner.nextLine();

        Materiel found = materielDao.getMaterielByName(nom);

        if (found == null) {
            System.out.print("Code : ");
            code = scanner.nextLine();
        } else {
            code = found.getCode();
            availability = found.isAvailable();
            System.out.println("\nLe materiel que vous voulez ajouter existe déja, quelle est la nouvelle quantité à ajouter ?\n");
        }

        while (true) {
            try {
                System.out.print("Quantité : ");
                quantity = scanner.nextInt();
                if (found != null) quantity += found.getQuantity();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nLa quantité doit être un chiffre et non un caractère\n");
                scanner.nextLine();
            }
        }

        materiel.setName(nom);
        materiel.setCode(code);
        materiel.setAvailability(availability);
        materiel.setQuantity(quantity);

        if (found == null) {
            materielDao.addMateriel(materiel);
            applicationPublisher.publish(new MyEvent<>(materiel, EventType.ADD));
        } else {
            materielDao.editMaterielQuantity(nom, quantity);
            applicationPublisher.publish(new MyEvent<>(materiel, EventType.ADDq));
        }
    }

    @Override
    public void supprimerMateriel(String nom) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            Allocation allocation = materielAllocatedDao.getAllocationByMateriel(materiel.getId());
            if (allocation == null) {
                materielDao.deleteMateriel(nom);
                System.out.println("\nLe materiel '" + nom + "' est supprimé avec succés");
            } else {
                System.out.println("\nAttention! il existe des allocations en cours sur ce materiel, vous ne pouvez le supprimer");
            }
        } else {
            System.out.println("\nOups! le nom '" + nom + "' ne correspond à aucun materiel");
        }
    }

    @Override
    public void modifierMateriel(String nom) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            Scanner scanner = new Scanner(System.in);
            String choice = "";
            while(true) {
                System.out.println("\n-----------------------------------------");
                System.out.println("Tapez 1 pour : modifier le nom");
                System.out.println("Tapez 2 pour : modifier le code");
                System.out.println("Tapez 3 pour : modifier la disponibilité");
                System.out.println("Tapez 4 pour : modifier le quantité");
                System.out.println("-----------------------------------------\n");

                System.out.print("option : ");
                choice = scanner.next();

                if ("1".equals(choice)) {
                    System.out.print("\nLe nouveau nom : ");
                    String nvNom = scanner.next();
                    materielDao.editMaterielName(nom, nvNom);
                    applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATEn));
                    break;
                } else if ("2".equals(choice)) {
                    System.out.print("\nLe nouveau code : ");
                    String code = scanner.next();
                    materielDao.editMaterielCode(nom, code);
                    applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATEc));
                    break;
                } else if ("3".equals(choice)) {
                    materielDao.editMaterielAvailability(nom, !materiel.isAvailable());
                    applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATEa));
                    break;
                } else if ("4".equals(choice)) {
                    int quantity = 0;
                    while (true) {
                        try {
                            System.out.print("\nLa nouvelle quantité : ");
                            quantity = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("\nLa quantité doit être un chiffre et non un caractère");
                            scanner.nextLine();
                        }
                    }
                    materielDao.editMaterielQuantity(nom, quantity);
                    applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATEq));
                    break;
                } else {
                    System.out.println("\n'" + choice + "' est un choix invalide, réessayez");
                }
            }
        } else {
            System.out.println("\nOups! le nom '" + nom + "' ne correspond à aucun materiel");
        }
    }

    @Override
    public void chercherMateriel(String nom) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            System.out.println("\nLes informations du materiel '" + nom + "' sont :");
            System.out.println(materiel);
        } else
            System.out.println("\nOups! Le nom '" + nom + "' ne correspond à aucun materiel");
    }

    @Override
    public void allouerMateriel(String nom, int userID) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            if (materiel.isAvailable()) {
                if (materiel.getQuantity() > 0){
                    Scanner scanner = new Scanner(System.in);
                    int quantity = 0;
                    while (true) {
                        while (true) {
                            try {
                                System.out.print("\nEntrer la quantité : ");
                                quantity = scanner.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("\nLa quantité doit être un chiffre et non un caractère");
                                scanner.nextLine();
                            }
                        }
                        if (quantity <= materiel.getQuantity()) {
                            Allocation allocation = materielAllocatedDao.ifMaterielIsAllocatedByUser(userID, materiel.getId());
                            if (allocation == null) {
                                materielDao.allocateMateriel(materiel, userID, quantity, false, 0);
                            } else {
                                materielDao.allocateMateriel(materiel, userID, quantity, true, allocation.getQuantity());
                            }
                            System.out.println("\nVous avez alloué " + quantity + " '" + materiel.getName() + "(s)' avec succès");
                            break;
                        } else {
                            System.out.println("\nLa quantité du materiel '" + nom + "' disponible est : " + materiel.getQuantity());
                        }
                    }
                } else {
                    System.out.println("\nLe materiel '" + nom + "' est en rupture de stock");
                }
            } else {
                System.out.println("\nLe materiel '" + nom + "' est non disponible");
            }
        } else {
            System.out.println("\nOups! le nom '" + nom + "' ne correspond à aucun materiel");
        }
    }

    @Override
    public void rendreMateriel(String nom, int userID) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            Allocation allocation = materielAllocatedDao.ifMaterielIsAllocatedByUser(userID, materiel.getId());
            if (allocation != null) {
                Scanner scanner = new Scanner(System.in);
                int quantity = 0;
                while (true) {
                    try {
                        System.out.print("\nEntrer la quantité : ");
                        quantity = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nLa quantité doit être un chiffre et non un caractère");
                        scanner.nextLine();
                    }
                }
                if (quantity <= allocation.getQuantity()) {
                    materielDao.returnMateriel(materiel, allocation, quantity, userID);
                    System.out.println("\nVous avez rendu " + quantity + " '" + nom + "(s)' avec succès");
                } else
                    System.out.println("\nLa quantité alloué est seulement : " + allocation.getQuantity());
            } else {
                System.out.println("\nCe materiel n'est pas alloué");
            }
        } else {
            System.out.println("\nOups! le nom '" + nom + "' ne correspond à aucun materiel");
        }
    }

    @Override
    public void changerDisponibilte(String nom) {
        Materiel materiel = materielDao.getMaterielByName(nom);
        if (materiel != null) {
            materielDao.editMaterielAvailability(nom, !materiel.isAvailable());
            applicationPublisher.publish(new MyEvent<>(materiel, EventType.UPDATEq));
        } else {
            System.out.println("\nOups! le nom '" + nom + "' ne correspond à aucun materiel");
        }
    }
}
