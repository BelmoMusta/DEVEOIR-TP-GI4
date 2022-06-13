

DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS utilisateur;


CREATE TABLE utilisateur
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role varchar(250) DEFAULT 'employe'
);



CREATE TABLE materiel
(
    id_materiel INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id VARCHAR(250) DEFAULT NULL, 
    utilisateur_username VARCHAR(250) DEFAULT NULL references utilisateur(username),
    name VARCHAR(250) NOT NULL,
    code VARCHAR(250) NOT NULL,
    disponible BOOLEAN DEFAULT true ,
    alloue BOOLEAN DEFAULT FALSE,
    duree varchar(250) NULL,
    CONSTRAINT utilisateur_id foreign key (utilisateur_id) references utilisateur(id)
 );