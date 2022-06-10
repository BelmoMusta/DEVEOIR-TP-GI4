DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS materiel_details;

CREATE TABLE materiel_details(
                                 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 type VARCHAR(20) NOT NULL,
                                 author VARCHAR(100) NULL,
                                 madeof VARCHAR(100) NULL
);

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    type INT NOT NULL,
    stock INT NOT NULL,
    allocated INT NOT NULL DEFAULT 0,
    FOREIGN KEY (type) REFERENCES materiel_details(id)
);

CREATE TABLE user(
                     id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                     username VARCHAR(100) NOT NULL UNIQUE,
                     password VARCHAR(250) NOT NULL,
);

CREATE TABLE roles(
                      id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                      name VARCHAR(20) NOT NULL
);

CREATE TABLE user_roles(
                           id_user INT NOT NULL,
                           id_role INT NOT NULL,
                           foreign key (id_user) REFERENCES user(id),
                           foreign key (id_role) REFERENCES roles(id)
);

