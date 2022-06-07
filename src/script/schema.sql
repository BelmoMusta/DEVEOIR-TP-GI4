DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,
    code VARCHAR(250) NOT NULL,
    allouer int(50) NULL,
    duree VARCHAR(250) NULL,
    dispo BOOLEAN DEFAULT true ,
      CONSTRAINT allouer foreign key (allouer) references User(id),

);

DROP TABLE IF EXISTS User;

CREATE TABLE User
(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(200) NOT NULL,
password VARCHAR(200) NOT NULL,
role VARCHAR(200) NOT NULL,
);

DROP TABLE IF EXISTS Allocation;

CREATE TABLE Allocation
(
   id int AUTO_INCREMENT PRIMARY KEY,
   idMateriel VARCHAR(100) NOT NULL,
   idUser VARCHAR(100) NOT NULL,
   duree int NOT NULL,

);