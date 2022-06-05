DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    quantite varchar(250) NOT NULL,
    disponible BOOLEAN DEFAULT true 

);

CREATE TABLE users
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    pw VARCHAR(250) NOT NULL,
    role varchar(250) DEFAULT 'employe',
    alloue INT NULL,
  foreign key (alloue) references materiel(id)
);
