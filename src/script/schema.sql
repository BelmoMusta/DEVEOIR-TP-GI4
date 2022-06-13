DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    idM   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    qte  INT,
    
    disponible BOOLEAN,
    

);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    idR   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,
);

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    idU   INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,
 
    idR INT,

    FOREIGN KEY (idR) REFERENCES roles(idR),

);

DROP TABLE IF EXISTS allocations;

CREATE TABLE allocations
(
    idA  INT AUTO_INCREMENT PRIMARY KEY,

    qta  INT,

     rendu BOOLEAN,

      idM INT,

      FOREIGN KEY (idM) REFERENCES materiel(idM),
     
       idU INT,

       FOREIGN KEY(idU) REFERENCES users(idU)
);



