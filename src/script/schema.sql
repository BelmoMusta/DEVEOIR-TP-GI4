DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;


CREATE TABLE materiel
(
    idMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    materielName VARCHAR(250) NOT NULL,

    materielCode VARCHAR(250) NOT NULL,

    materielType VARCHAR(250) NOT NULL,

    isDisponible BOOLEAN NOT NULL,
);

CREATE TABLE user
(
    idUser   INT AUTO_INCREMENT PRIMARY KEY,

    userName VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,

);

