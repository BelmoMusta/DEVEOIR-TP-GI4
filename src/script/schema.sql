DROP TABLE IF EXISTS livre;

CREATE TABLE livre
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    stock INT NOT NULL,
    
    disponibilite BOOLEAN NOT NULL,

);

DROP TABLE IF EXISTS chaise;

CREATE TABLE chaise
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    stock INT NOT NULL,
    
    disponibilite BOOLEAN NOT NULL,

);

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id   INT auto_increment PRIMARY KEY ,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

);

DROP TABLE IF EXISTS role;

CREATE TABLE role
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role
(
    role_id  INT NOT NULL REFERENCES role(id),

    user_id INT NOT NULL REFERENCES user(id),

);
DROP TABLE IF EXISTS user_livre;

CREATE TABLE user_livre
(
    livre_id  INT NOT NULL REFERENCES livre(id),

    user_id INT NOT NULL REFERENCES user(id),

);
DROP TABLE IF EXISTS user_chaise;

CREATE TABLE user_chaise
(
    chaise_id  INT NOT NULL REFERENCES chaise(id),

    user_id INT NOT NULL REFERENCES user(id),

);
