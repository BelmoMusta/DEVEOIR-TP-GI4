DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    pw VARCHAR(250) NOT NULL,
    role varchar(250) DEFAULT 'employe',

);

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    allouer int(100) NULL,
    duree VARCHAR(250) NULL,
    disponible BOOLEAN DEFAULT true ,
      CONSTRAINT allouer foreign key (allouer) references users(id),
    

);



