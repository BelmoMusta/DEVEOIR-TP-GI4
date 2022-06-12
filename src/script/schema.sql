DROP TABLE IF EXISTS materiel;

DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS gestionAllocation;
CREATE TABLE person
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role VARCHAR(250) NOT NULL ,

);

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    type VARCHAR(250) NOT NULL,

    isAvailable BOOLEAN  NOT NULL,

    isAllocated BOOLEAN  NOT NULL DEFAULT FALSE,

    Quantite INT DEFAULT 5,


);
CREATE TABLE gestionAllocation
(
    idAllocation   INT AUTO_INCREMENT PRIMARY KEY,
    idMateriel INT NOT NULL ,
    NomMateriel VARCHAR(250) NOT NULL,
    idUser INT NOT NULL,
    NomUser VARCHAR(250) NOT NULL,
    duree INT NOT NULL ,
    FOREIGN KEY (idMateriel) REFERENCES materiel(id),
    FOREIGN KEY (idUser) REFERENCES person(id),

);




