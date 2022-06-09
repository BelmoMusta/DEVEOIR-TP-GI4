package com.ensa.gi4.utils;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationPropertiesStrings {

    // application's menus initialised here with application properties file
    @Value("${menu.admin}")
    protected String adminMenu;
    @Value("${menu.material.type}")
    protected String materialTypeMenu;
    @Value("${menu.available.material}")
    protected String makeAvailableMenu;
    @Value("${menu.available/unavailable.confirmation}")
    protected String confirmationMenu;
    @Value("${menu.employee}")
    protected String employeeMenu;
    @Value("${menu.search}")
    protected String searchTypeMenu;

    // application's error messages initialised here with application properties file
    @Value("${error.invalid.stock}")
    protected String invalidStock;
    @Value("${error.material.type.not.found}")
    protected String materialTypeNotFoundError;
    @Value("${error.invalide.choice}")
    protected String invalideChoice;
    @Value("${error.empty.list}")
    protected String emptyList;
    @Value("${error.material.out.stock}")
    protected String outOfStock;
    @Value("${error.material.unavailable}")
    protected String unavailableMaterial;
    @Value("${error.warning.deadline}")
    protected String deadlineMessage;
    @Value("${error.empty.material.type}")
    protected String emptyMaterialType;
    @Value("${error.at.least.one.material.is.rented}")
    protected String atLeastOneMaterialIsRentedError;

}
