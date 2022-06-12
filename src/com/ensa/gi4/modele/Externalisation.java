package com.ensa.gi4.modele;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Externalisation {
    @Value("${msgAjout}")
	public String msgAjout;
    @Value("${msgAjoutInvalid}")
	public String msgAjoutInvalid;
@Value("${msgAjoutCodeLivre}")
public String msgAjoutCodeLivre;
@Value("${msgAjoutCodeChaise}")
public String msgAjoutCodeChaise;
@Value("${msgModifie}")
public String msgModifie;
@Value("${msgModifierId}")
public String msgModifierId;
@Value("${msgModifierCode}")
public String msgModifierCode;
@Value("${msgSupprimer}")
public String msgSupprimer; 
@Value("${msgSupprimerId}")
public String msgSupprimerId;
@Value("${msgIdNonExist}")
public String msgIdNonExist;
@Value("${msgListerMateriel}")
public String msgListerMateriel; 
@Value("${msgAllouer}")
public String msgAllouer;
@Value("${msgAllouerLivre}")
public String msgAllouerLivre;
@Value("${msgAllouerChaise}")
public String msgAllouerChaise;
@Value("${msgDuree}")
public String msgDuree;
@Value("${msgDisponible}")
public String msgDisponible;
@Value("${msgMaterielDisponible}")
public String msgMaterielDisponible;
@Value("${msgCreerCompte}")
public String msgCreerCompte;
@Value("${msgChercher}")
public String msgChercher;

@Value("${msgRendre}")
public String msgRendre;
@Value("${msgIdMaterielRendre}")
public String msgIdMaterielRendre; 
@Value("${msgAfficherAllouer}")
public String msgAfficherAllouer;
@Value("${msgAllouerUser}")
public String msgAllouerUser;
@Value("${msgChoixInvalid}")
public String msgChoixInvalid;
@Value("${msgSaisirId}")
public String msgSaisirId;
@Value("${msgBonjour}")
public String msgBonjour;
@Value("${msgSaisirNom}")
public String msgSaisirNom;
@Value("${msgSaisirMotDePasse}")
public String msgSaisirMotDePasse;
@Value("${msgSaisirNomUser}")
public String msgSaisirNomUser;
@Value("${msgCreationDeCompte}")
public String msgCreationDeCompte;
@Value("${msgEtreEmloye}")
public String msgEtreEmloye; 
@Value("${msgEtreAdmin}")
public String msgEtreAdmin;
@Value("${msgCreationCompteFailure}")
public String msgCreationCompteFailure;
@Value("${msgAjouterLivre}")
public String msgAjouterLivre;
@Value("${msgAjoutChaise}")
public String msgAjoutChaise;


}
