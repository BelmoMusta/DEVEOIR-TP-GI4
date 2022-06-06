DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS allouer;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    quantite int NOT NULL,
    disponible BOOLEAN DEFAULT true 

);

CREATE TABLE users
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    pw VARCHAR(250) NOT NULL,
    role varchar(250) DEFAULT 'employe',

);
CREATE TABLE allouer
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    user_id VARCHAR(250) NOT NULL,

    materiel_id VARCHAR(250) NOT NULL,
    duree varchar(250) NOT NULL,
    
    CONSTRAINT user_id foreign key (user_id) references users(id),
     CONSTRAINT materiel_id foreign key (materiel_id) references materiel(id)
);
