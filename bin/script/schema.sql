DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS utilisateur;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    quantite int NOT NULL,
    disponible BOOLEAN DEFAULT true 

);

CREATE TABLE utilisateur
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role varchar(250) DEFAULT 'employe',
    alloue INT NULL,
    foreign key (alloue) references materiel(id)
);

