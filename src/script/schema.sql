DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS allocations;

CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    availability INT NOT NULL,

    _available BOOL NOT NULL,
);
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    type VARCHAR(250) NOT NULL,
);

CREATE TABLE allocations
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    userId INT NOT NULL,

    materielId INT NOT NULL,

    quantity INT NOT NULL,
/*CONSTRAINT FK_users*/
    FOREIGN KEY (userId) REFERENCES user(id),

    FOREIGN KEY (materielId) REFERENCES materiel(id)
);




