DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    dispo INTEGER(250) NOT NULL,

    alloué INTEGER(250) NOT NULL,

     user INTEGER(250) ,
);


