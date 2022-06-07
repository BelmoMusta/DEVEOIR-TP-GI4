DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    type VARCHAR(250) NOT NULL,

    stock INT,

    dispo BOOLEAN

);

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,

);

DROP TABLE IF EXISTS allocation;

CREATE TABLE allocation
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    idUser INT NOT NULL,

    idMat INT NOT NULL,

    allocationDuration TIMESTAMP NOT NULL,

    CONSTRAINT FK_idUser FOREIGN KEY (idUser) REFERENCES user(id),

    CONSTRAINT FK_idMat FOREIGN KEY (idMat) REFERENCES materiel(id),

);

