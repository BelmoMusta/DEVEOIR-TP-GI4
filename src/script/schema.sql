DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    id   Long AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,

    dispo Long NOT NULL,

    allouer Long NOT NULL,

    iduser VARCHAR(250),


);


