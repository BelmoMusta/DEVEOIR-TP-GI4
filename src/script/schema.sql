DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,
    
    role VARCHAR(250) NOT NULL,

);
CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    dispo BOOLEAN ,
    
    userId INT ,
    
    FOREIGN KEY (userId) REFERENCES users(id) ,

    


);




