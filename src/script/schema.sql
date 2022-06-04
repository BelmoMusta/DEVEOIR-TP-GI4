DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS allocation;
DROP TABLE IF EXISTS allocation_details;

CREATE TABLE materiel
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    wood VARCHAR(250) NULL,
    author VARCHAR(250) NULL,
    allocated BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE user(
     id INT AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(100) NOT NULL UNIQUE,
     password VARCHAR(256) NOT NULL,
     email VARCHAR(100) NOT NULL UNIQUE,
     registration_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
     locked BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE allocation_details(
    id INT AUTO_INCREMENT PRIMARY KEY,
    materiel_id INT NOT NULL,
    user_id INT NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    foreign key (materiel_id) references materiel(id),
    foreign key (user_id) references user(id)
);

CREATE TABLE roles(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_roles(
    id_user INT NOT NULL,
    id_role INT NOT NULL,
    foreign key (id_user) references user(id),
    foreign key (id_role) references roles(id)
);





