package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.MaterialType;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterialService;
import com.ensa.gi4.utils.ApplicationPropertiesStrings;
import com.ensa.gi4.utils.CustomScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component("employeeController")
public class GestionMaterialController extends ApplicationPropertiesStrings {

    @Autowired
    protected ApplicationPublisher publisher;
    @Autowired
    protected User authenticatedUser;
    @Autowired
    protected GestionMaterialService gestionMaterialService;

    public void menuElement() {
        System.out.println(employeeMenu);
    }

    public void showMenu() {
        this.checkIfUserShouldReturnMaterialToday();
        this.menuElement();
        String userInput = CustomScannerUtil.scanForString();
        this.checkInputChoices(userInput);
    }

    private void checkIfUserShouldReturnMaterialToday() {
        Integer numberOfMaterialToReturn = gestionMaterialService.getNumberOfMaterialToReturnToday(authenticatedUser.getId());
        if (numberOfMaterialToReturn == 0) {
            return;
        }
        String warningMessage = String.format(deadlineMessage, numberOfMaterialToReturn);
        System.out.println(warningMessage);
    }

    public void checkInputChoices(String userInput) {
        switch (userInput) {
            case "0":
                this.closeApplication();
                break;
            case "1":
                this.chooseSearchType();
                break;
            case "2":
                this.rentOneMaterial();
                break;
            case "3":
                this.returnRentedMaterial();
                break;
            case "4":
                this.showUserRentedMaterials();
                break;
            case "5":
                this.showMaterialList(gestionMaterialService.showMaterialList());
                break;
            default:
                System.out.println(invalideChoice);
                break;
        }
    }

    public void chooseSearchType() {
        String userChoice = CustomScannerUtil.scanForString(searchTypeMenu);
        switch(userChoice) {
            case "1":
                this.searchMaterialById();
                break;
            case "2":
                this.searchMaterialByType();
                break;
            case "0":
                break;
            default:
                System.out.println(invalideChoice);
                break;
        }
    }

    public void searchMaterialByType() {
        System.out.println("Choisir le type recherche:");
        MaterialType searchedMaterialType = this.selectOneMaterialType();
        if (checkForNullObjects(emptyMaterialType, searchedMaterialType)) return;
        List<Material> searchedMaterial = gestionMaterialService.searchMaterialByType(searchedMaterialType.getId());
        this.showMaterialList(searchedMaterial);
    }

    public void searchMaterialById() {
        Long materialId = CustomScannerUtil.scanForLong("Entrer l'identifiant du material recherche:");
        Material searchedMaterial = gestionMaterialService.searchMaterialById(materialId);
        if (checkForNullObjects(emptyList, searchedMaterial)) return;
        this.showMaterialList(Arrays.asList(searchedMaterial));
    }

    public void rentOneMaterial() {
        RentedMaterial materialRentRecord = new RentedMaterial();
        if (!this.showMaterialList(gestionMaterialService.showMaterialList())) return;
        Long materialId = CustomScannerUtil.scanForLong("selectionner dans le menu ci-dessus l'identifiant du materiel a allouer:");
        Material materialToBeRented = gestionMaterialService.searchMaterialById(materialId);
        if (checkForNullObjects(invalideChoice, materialToBeRented)) return;
        if (gestionMaterialService.canMaterialBeRented(materialToBeRented)) {
            System.out.println(outOfStock);
            return;
        } else if (!materialToBeRented.isAvailable()){
            System.out.println(unavailableMaterial);
            return;
        }
        Long rentingDuration = CustomScannerUtil.scanForLong("Entrer la duree de location en jours:");
        materialRentRecord.setUserId(authenticatedUser.getId());
        materialRentRecord.setMaterial(materialToBeRented);
        materialRentRecord.setDeadline(LocalDate.now().plusDays(rentingDuration));
        gestionMaterialService.rentOneMaterial(materialRentRecord);
        System.out.println("le material a ete alloue avec succe");
    }

    public void returnRentedMaterial() {
        if (!this.showUserRentedMaterials()) return;
        Long returnedMaterialId = CustomScannerUtil.scanForLong("\nselectionner dans le menu ci-dessus l'identifiant du materiel e rendre:");
        int rowAffected = gestionMaterialService.returnRentedMaterial(returnedMaterialId);
        if (rowAffected == 0) {
            System.out.println(invalideChoice);
            return;
        }
        System.out.println(rowAffected + " materiel(s) a ete rendu avec succe");
    }

    public boolean showUserRentedMaterials() {
        List<RentedMaterial> rentedMaterials = gestionMaterialService.getUserRentedMaterials(authenticatedUser.getId());
        if (rentedMaterials.isEmpty()) {
            System.out.println(emptyList);
            return false;
        }
        this.showRentedMaterialList(rentedMaterials);
        return true;
    }

    public void showRentedMaterialList(List<RentedMaterial> rentedMaterials) {
        String menuHeader = String.format("%-20s%-20s%-20s%-20s","Id", "Nom", "Type", "Date de rendre (aaaa-mm-jj)");
        System.out.println(menuHeader);
        for (RentedMaterial rentedMaterial: rentedMaterials) {
            String line = String.format("%-20s%-20s%-20s%-20s", rentedMaterial.getId(),
                    rentedMaterial.getMaterial().getName(), rentedMaterial.getMaterial().getMaterialType().getName(),
                    rentedMaterial.getDeadline());
            System.out.println(line);
        }
        System.out.println();
    }

    public boolean showMaterialList(List<Material> materialList) {
        if (materialList.isEmpty()) {
            System.out.println(emptyList);
            return false;
        }
        String menuHeader = String.format("%-20s%-20s%-20s%-20s%-20s%-20s","Id","Nom","type","stock","stock loue","disponibilite");
        System.out.println(menuHeader);
        for (Material material: materialList) {
            String line = String.format("%-20s%-20s%-20s%-20s%-20s%-20s", material.getId(),
                    material.getName(), material.getMaterialType().getName(), material.getStock(),
                    material.getTimeRented(), material.isAvailable() ? "Oui" : "Non");
            System.out.println(line);
        }
        return true;
    }

    public MaterialType selectOneMaterialType() {
        List<MaterialType> materialTypes = gestionMaterialService.getAllMaterialType();
        if (materialTypes.isEmpty()) {
            return null;
        }
        this.showMaterialTypeList(materialTypes);
        int materialTypeIndex = CustomScannerUtil.scanForInt();
        while (materialTypeIndex >= materialTypes.size()) {
            materialTypeIndex = CustomScannerUtil.scanForInt("Veuillez saisir une option valide pour continuer:");
        }
        return materialTypes.get(materialTypeIndex);
    }

    public void showMaterialTypeList(List<MaterialType> materialTypes) {
        for (MaterialType materialType: materialTypes) {
            String line = String.format("%s- %s", materialTypes.indexOf(materialType), materialType.getName());
            System.out.println(line);
        }
    }

    public boolean checkForNullObjects(String errorMessage, Object checkedObject) {
        if (checkedObject == null) {
            System.out.println(errorMessage);
            return true;
        }
        return false;
    }

    public void closeApplication() {
        System.exit(0);
    }

}
