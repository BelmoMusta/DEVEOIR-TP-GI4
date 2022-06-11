DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    indisponible BIT ,
    alloue BIT ,
    non_alloue BIT ,
    user VARCHAR(250),


);
CREATE TABLE users(

    id   INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(250) NOT NULL,
    name VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,

);


