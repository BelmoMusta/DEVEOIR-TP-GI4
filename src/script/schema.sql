DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS materielAllocated;

CREATE TABLE users
(
    id INT AUTO_INCREMENT NOT NULL,

    username VARCHAR(250) PRIMARY KEY,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL
);

CREATE TABLE materiel
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    availability BOOL NOT NULL,

    quantity INT NOT NULL

);

CREATE TABLE materielAllocated
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    userID INT NOT NULL,

    materielID INT NOT NULL,

    quantity INT NOT NULL,

    CONSTRAINT FK_users FOREIGN KEY (userID) REFERENCES users(id),

    CONSTRAINT FK_materiel FOREIGN KEY (materielID) REFERENCES materiel(id)
);


