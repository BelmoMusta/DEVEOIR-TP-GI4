package com.ensa.gi4.controller;

import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.MaterialType;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.utils.CustomScannerUtil;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("adminController")
public class GestionMaterialAdminController extends GestionMaterialController {

    @Override
    public void menuElement() {
        super.menuElement();
        System.out.println(adminMenu);
    }

    @Override
    public void checkInputChoices(String userInput) {
        switch (userInput) {
            case "6":
                this.createNewMaterial();
                break;
            case "7":
                this.deleteMaterial();
                break;
            case "8":
                this.editMaterial();
                break;
            case "9":
                this.makeMaterialAvailableOrUnavailable();
                break;
            case "10":
                this.showRentedMaterialForEveryUser();
                break;
            default:
                super.checkInputChoices(userInput);
                break;
        }
    }

    public void createNewMaterial() {
        Material newMaterial = new Material();
        System.out.println("entrez les informations du nouveau materiel");
        System.out.println("Nom du materiel:");
        String name = CustomScannerUtil.scanForString();
        MaterialType typeOfNewMaterial = this.selectOrCreateMaterialType();
        if (checkForNullObjects("", typeOfNewMaterial)) return;
        int stock = CustomScannerUtil.scanForInt("Entrer le stock");
        String isAvailable = CustomScannerUtil.scanForString(makeAvailableMenu);
        this.initialiseMaterial(newMaterial, name, typeOfNewMaterial, stock, "1".equals(isAvailable));
        gestionMaterialService.addNewMaterial(newMaterial);
        publisher.publish(new MyEvent<>(newMaterial, EventType.ADD));
    }

    public void deleteMaterial() {
        if (!this.showMaterialList(gestionMaterialService.showMaterialList())) return;
        Long materialIdToBeDeleted = CustomScannerUtil.scanForLong("Selectionner dans le menu ci-dessus l'identifiant du material a supprimer:");
        int returnValue = gestionMaterialService.deleteMaterial(materialIdToBeDeleted);
        if (returnValue == 0) {
            System.out.println("Aucun materiel trouve avec l'identifiant insere");
        } else if (returnValue == -1){
            System.out.println(atLeastOneMaterialIsRentedError);
        } else {
            System.out.println(returnValue + " materiel(s) a ete supprime avec succee");
        }
    }

    public void editMaterial() {
        if (!this.showMaterialList(gestionMaterialService.showMaterialList())) return;
        Long materialId = CustomScannerUtil.scanForLong("Entrer l'identifiant du materiel a modifier:");
        Material materialToBeEdited = gestionMaterialService.searchMaterialById(materialId);
        if (checkForNullObjects(emptyList, materialToBeEdited)) return;
        String name = CustomScannerUtil.scanForString("entrer le nouveau nom du material:");
        MaterialType materialType = this.selectOrCreateMaterialType();
        if (checkForNullObjects("", materialType)) return;
        int stock = CustomScannerUtil.scanForInt("Entrer le nouveau stock de materiel");
        if (!this.isEnteredStockValid(stock, materialToBeEdited)) {
            String errorMessage = String.format(invalidStock, stock, materialToBeEdited.getTimeRented());
            System.out.println(errorMessage);
            return;
        }
        this.initialiseMaterial(materialToBeEdited, name, materialType, stock, materialToBeEdited.isAvailable());
        int rowAffected = gestionMaterialService.editMaterial(materialToBeEdited);
        System.out.println(rowAffected + " materiel(s) a ete modifier avec succee");
    }

    public MaterialType selectOrCreateMaterialType() {
        String userChoice = CustomScannerUtil.scanForString(materialTypeMenu);
        if ("1".equals(userChoice)) {
            MaterialType materialType = this.selectOneMaterialType();
            if (checkForNullObjects(materialTypeNotFoundError, materialType))
                return this.createNewMaterialType();
            return materialType;
        } else if ("2".equals(userChoice)) {
            return this.createNewMaterialType();
        } else {
            System.out.println(invalideChoice);
            return null;
        }
    }

    private MaterialType createNewMaterialType() {
        String materialName = CustomScannerUtil.scanForString("Entrez le nom de nouveau type du materiel").toUpperCase();
        Long materialTypeId = gestionMaterialService.addNewMaterialType(materialName);
        return new MaterialType(materialTypeId, materialName);
    }

    public boolean isEnteredStockValid(int stock, Material materialToBeEdited) {
        if (materialToBeEdited.getStock() < stock) {
            return true;
        } else {
            return materialToBeEdited.getTimeRented() <= stock;
        }
    }

    public void makeMaterialAvailableOrUnavailable() {
        if (!this.showMaterialList(gestionMaterialService.showMaterialList())) return;
        Long materialId = CustomScannerUtil.scanForLong("Selectionnez dans le menus ci-dessus l'identifiant du materiel a marquer disponible/indisponible:");
        Material selectedMaterial = gestionMaterialService.searchMaterialById(materialId);
        if (checkForNullObjects(invalideChoice, selectedMaterial)) return;
        String confirmationMessage = String.format(confirmationMenu, selectedMaterial.getName(),
                selectedMaterial.isAvailable() ? "indisponible" : "disponible");
        String userChoice = CustomScannerUtil.scanForString(confirmationMessage);
        if ("1".equals(userChoice)) {
            gestionMaterialService.makeMaterialAvailableOrUnavailable(selectedMaterial);
            String successMessage = String.format("le material %s est marque comme %s", selectedMaterial.getName(),
                    selectedMaterial.isAvailable() ? "disponible" : "indisponible");
            System.out.println(successMessage);
        } else if ("0".equals(userChoice)) {
            System.out.println(invalideChoice);
        }
    }

    public void showRentedMaterialForEveryUser() {
        List<User> usersThatRentedMaterials = gestionMaterialService.getRentedMaterialsByUsers();
        if (usersThatRentedMaterials.isEmpty()) {
            System.out.println("personne n'a loue de materiel");
            return;
        }
        System.out.println("Listes des utilisateurs qui ont loue des materiaux:\n");
        for (User user: usersThatRentedMaterials) {
            String userInfos = String.format("%s %s (%s)", user.getFirstName(), user.getLastName(), user.getUsername());
            String currentUserMessage = (user.getId()).equals(authenticatedUser.getId()) ? "Vous:" : userInfos;
            System.out.println(currentUserMessage);
            this.showRentedMaterialList(user.getRentedMaterials());
        }
    }

    public void initialiseMaterial(Material material, String name, MaterialType materialType, int stock, boolean isAvailable) {
        material.setName(name);
        material.setMaterialType(materialType);
        material.setStock(stock);
        material.setAvailable(isAvailable);
    }
}
