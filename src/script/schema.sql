DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS usermateriel;


CREATE TABLE materiel
(
    idMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250)  NOT NULL UNIQUE,

    typeMateriel VARCHAR(250) NOT NULL,

    isDisponible BOOLEAN NOT NULL,

    isAllouer BOOLEAN NOT NULL,
);

CREATE TABLE user
(
    idUser   INT AUTO_INCREMENT PRIMARY KEY,

    userName VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,
);

CREATE TABLE usermateriel
(
    idUserMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    idUser INT NOT NULL,

    code VARCHAR(250) NOT NULL,

    FOREIGN KEY(idUser) REFERENCES user,

    FOREIGN KEY(code) REFERENCES materiel,
);