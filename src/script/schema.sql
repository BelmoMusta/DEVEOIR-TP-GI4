DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    is_rented boolean NOT NULL

);

DROP TABLE IF EXISTS user;

CREATE TABLE user(

    id_user LONG AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(100) NOT NULL,

    mdp VARCHAR(200) NOT NULL,

    role_user VARCHAR(100) NOT NULL,
);

DROP TABLE IF EXISTS rented_materiel;

CREATE TABLE rented_materiel
(
    id LONG AUTO_INCREMENT PRIMARY KEY,

    id_user LONG,

    id_materiel LONG,

    FOREIGN KEY(id_materiel) REFERENCES rented_materiel(id),

    FOREIGN KEY(id_user) REFERENCES user_table(id_user),

);
/*FOREIGN KEY(id_rented) REFERENCES rented_materiel(id),
FOREIGN KEY(id_user) REFERENCES user_table(id_user),*/


