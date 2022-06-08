DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS AllocatedItems;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    allocated INT DEFAULT 0,
    stock INT DEFAULT 5,
    available VARCHAR(250) NOT NULL DEFAULT 'yes'
);

CREATE TABLE user(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
);

CREATE TABLE role(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_roles(
    id_user INT NOT NULL,
    id_role INT NOT NULL,
    foreign key (id_user) references user(id),
    foreign key (id_role) references role(id)
);
CREATE TABLE AllocatedItems(
    id_user INT NOT NULL,
    id_materiel INT NOT NULL
)

