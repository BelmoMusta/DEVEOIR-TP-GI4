DROP TABLE IF EXISTS users;

CREATE TABLE Users
(
    id  LONG AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(250) NOT NULL,

    password VARCHAR(250) NOT NULL,

    role VARCHAR(250) NOT NULL,


);