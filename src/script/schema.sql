DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(

    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role     VARCHAR(250) NOT NULL,
);


CREATE TABLE materiel
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id       VARCHAR(250) DEFAULT NULL,
    username VARCHAR(250) DEFAULT NULL references user (username),
    name          VARCHAR(250) NOT NULL,
    code          VARCHAR(250) NOT NULL,
    disponible    BOOLEAN      DEFAULT true,
    alloue        BOOLEAN      DEFAULT FALSE,
    duree         varchar(250) NULL,
    type          VARCHAR(250) NOT NULL,
    CONSTRAINT user_id foreign key (user_id) references user (id)
);


