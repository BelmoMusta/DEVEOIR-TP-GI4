DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS materielAllocated;


CREATE TABLE materiel
(
    idMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    materielName VARCHAR(250) NOT NULL,

    materielCode VARCHAR(250) NOT NULL,

    materielType VARCHAR(250) NOT NULL,

    isDisponible BOOLEAN NOT NULL,

    isAllocated BOOLEAN NOT NULL,
);

CREATE TABLE user
(
    idUser   INT AUTO_INCREMENT PRIMARY KEY,

    userName VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,

);
CREATE TABLE materielAllocated
(
    idMatAll   INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    idMateriel INT NOT NULL,
    quantity   INT NOT NULL,
    nb_days  INT NOT NULL,
    FOREIGN KEY(idUser) REFERENCES user,
    FOREIGN KEY(idMateriel) REFERENCES materiel,
);

