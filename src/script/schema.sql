DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS allocation;

CREATE TABLE materiel
(
    idMat   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    type VARCHAR(250) NOT NULL,

    isAvailable BOOLEAN NOt NULL,

    code VARCHAR(250)  NOT NULL UNIQUE,
);

CREATE TABLE utilisateur
(
    idUser   INT AUTO_INCREMENT PRIMARY KEY,

    userName VARCHAR(250) NOT NULL UNIQUE,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,

);

CREATE TABLE allocation
(
    idAlloc   INT AUTO_INCREMENT PRIMARY KEY,

    idUser INT NOT NULL,

    code VARCHAR(250)  NOT NULL,

    temps VARCHAR(250)  NOT NULL,


);


