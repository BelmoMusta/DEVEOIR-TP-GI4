DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS allocation;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users_roles;

CREATE TABLE materiel
(
    id_materiel INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
 
    dispo VARCHAR(250) NOT NULL,
);

CREATE TABLE users
(
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
);

CREATE TABLE allocation
(
  id_allocation INT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  FOREIGN KEY(code) REFERENCES materiel(code),
  FOREIGN KEY(username) REFERENCES users(username)
  
);

CREATE TABLE roles
(
  id_role INT AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(250) NOT NULL,
);

CREATE TABLE users_roles
(
  username VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL, 
  FOREIGN KEY(username) REFERENCES users(username),
  FOREIGN KEY(role) REFERENCES roles(role)
);
