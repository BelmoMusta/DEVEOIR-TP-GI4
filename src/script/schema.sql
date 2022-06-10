DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS utilisateur;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    etat VARCHAR(50) NOT NULL,

);

CREATE TABLE utilisateur 
(
	idUser INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL,
);

CREATE TABLE materiel_utilisateur 
(
	idUser INT,
    idMateriel INT,
    FOREIGN KEY (idUser) REFERENCES utilisateur(idUser),
    FOREIGN KEY (idMateriel) REFERENCES materiel(id),
);


