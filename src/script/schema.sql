DROP TABLE IF EXISTS materiel;

/*CREATE TABLE materiel
(
    'id'   INT AUTO_INCREMENT PRIMARY KEY,

    'name' VARCHAR(250) NOT NULL,

    'code' VARCHAR(250) NOT NULL,

);*/
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS `users` (
                                       `id`         LONG  PRIMARY KEY AUTO_INCREMENT,
                                       `name`       VARCHAR(250) NOT NULL,
                                       `password`   VARCHAR(250) NOT NULL,
                                       `role`       VARCHAR(250) NOT NULL
);
CREATE TABLE IF NOT EXISTS `materiel` (
                                          `id`         LONG  PRIMARY KEY AUTO_INCREMENT,
                                          `name`       VARCHAR(250) NOT NULL,
                                          `code`       VARCHAR(250) NOT NULL,
                                          `stock`       INTEGER  NOT NULL,
                                          `disponibility`  BOOLEAN NOT NULL
);
DROP TABLE IF EXISTS allocation;
CREATE TABLE IF NOT EXISTS `allocation` (
                                          `id`         LONG  PRIMARY KEY AUTO_INCREMENT,
                                          `userId`          LONG NOT NULL,
                                          `materialId`       LONG NOT NULL,
                                          `date`       DATE  NOT NULL
);





