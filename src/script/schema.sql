DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user_allocate_material;
DROP TABLE IF EXISTS user;

CREATE TABLE materiel
(
    idMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    type VARCHAR(250) NOT NULL,

    isFree BOOLEAN NOT NULL,

    isAllocate BOOLEAN NOT NULL,

);

CREATE TABLE user
(
    idUser   INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,
);
CREATE TABLE user_allocate_material
(
    idUserMateriel   INT AUTO_INCREMENT PRIMARY KEY,

    idUser INT NOT NULL,

    idMateriel VARCHAR(250) NOT NULL,

    FOREIGN KEY(idUser) REFERENCES user,

    FOREIGN KEY(idMateriel) REFERENCES materiel,
);

