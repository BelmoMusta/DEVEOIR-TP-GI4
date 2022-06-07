DROP TABLE IF EXISTS materiel;


DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,
    role VARCHAR(250) NOT NULL,

);
CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    
     alloue INT  null,
    disponible BOOLEAN DEFAULT true,
    dateAllocation VARCHAR(250),
     CONSTRAINT  alloue FOREIGN KEY (alloue) REFERENCES users(id),
    

);

